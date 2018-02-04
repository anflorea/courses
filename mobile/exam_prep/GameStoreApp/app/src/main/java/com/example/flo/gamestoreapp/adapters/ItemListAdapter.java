package com.example.flo.gamestoreapp.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flo.gamestoreapp.EditItemActivity;
import com.example.flo.gamestoreapp.EmployeeActivity;
import com.example.flo.gamestoreapp.OwnedItemsActivity;
import com.example.flo.gamestoreapp.R;
import com.example.flo.gamestoreapp.components.GsonComponent;
import com.example.flo.gamestoreapp.data.ItemContract;
import com.example.flo.gamestoreapp.data.ItemDbHelper;
import com.example.flo.gamestoreapp.domain.Item;
import com.example.flo.gamestoreapp.services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flo on 30/01/2018.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {
    public static final String TAG = ItemListAdapter.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private SQLiteDatabase mDb;

    private List<Item> mListData;
    Context mContext;

    private boolean mIsEmployeeAdater = true;

    public ItemListAdapter(Context context) {
        mContext = context;

        ItemDbHelper dbHelper = new ItemDbHelper(mContext);
        mDb = dbHelper.getWritableDatabase();
    }
    public ItemListAdapter(Context context, boolean isEmployee) {
        mContext = context;
        mIsEmployeeAdater = isEmployee;

        ItemDbHelper dbHelper = new ItemDbHelper(mContext);
        mDb = dbHelper.getWritableDatabase();
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;
        private TextView mQuantity;
        private TextView mType;
        private TextView mStatus;
        private TextView mId;
        private Button mBuyButton;
        private LinearLayout mBuyLayout;
        private EditText mBuyEditText;

        private LinearLayout mLayoutStatus;

        public ItemListViewHolder(View listItemView) {
            super(listItemView);

            mName = (TextView) listItemView.findViewById(R.id.name);
            mQuantity = (TextView) listItemView.findViewById(R.id.quantity);
            mType = (TextView) listItemView.findViewById(R.id.type);
            mStatus = (TextView) listItemView.findViewById(R.id.status);
            mId = (TextView) listItemView.findViewById(R.id.itemId);
            mLayoutStatus = (LinearLayout) listItemView.findViewById(R.id.layout_status);
            mBuyButton = (Button) listItemView.findViewById(R.id.buy_item_button);
            mBuyLayout = (LinearLayout) listItemView.findViewById(R.id.buy_item_container);
            mBuyEditText = (EditText) listItemView.findViewById(R.id.buy_quantity_edit_text);

            if (!mIsEmployeeAdater) {
                mLayoutStatus.setVisibility(View.INVISIBLE);
                mBuyButton.setOnClickListener(this);
            } else {
                listItemView.setOnClickListener(this);
                mBuyLayout.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Item item = mListData.get(adapterPosition);

            if (mIsEmployeeAdater) {
                Intent intent = new Intent(mContext, EditItemActivity.class);
                intent.putExtra(Item.class.getSimpleName(), GsonComponent.getInstance().getGson().toJson(item));

                mContext.startActivity(intent);
            } else {
                String quantityString = mBuyEditText.getText().toString();
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityString);
                    if (quantity > item.getQuantity()) {
                        Toast.makeText(mContext, "The selected quantity is not available", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Please enter a valid quantity", Toast.LENGTH_LONG).show();
                    return;
                }
                buyGame(item, quantity);
            }
        }
    }

    private void buyGame(final Item item, final int quantity) {
        Item toBuyItem = new Item();

        toBuyItem.setId(item.getId());
        toBuyItem.setQuantity(quantity);

        Call<Item> call = networkService.buyItem(item);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.d(TAG, "Buy successful!");

                Cursor cursor = mDb.query(
                        ItemContract.ItemEntry.TABLE_NAME,
                        null,
                        ItemContract.ItemEntry._ID + "=?",
                        new String[]{String.valueOf(item.getId())},
                        null,
                        null,
                        null
                );

                if (cursor.getCount() == 0) {
                    ContentValues cv = new ContentValues();
                    cv.put(ItemContract.ItemEntry._ID, item.getId());
                    cv.put(ItemContract.ItemEntry.COLUMN_NAME, item.getName());
                    cv.put(ItemContract.ItemEntry.COLUMN_TYPE, item.getType());
                    cv.put(ItemContract.ItemEntry.COLUMN_QUANTITY, quantity);
                    cv.put(ItemContract.ItemEntry.COLUMN_STATUS, item.getStatus());

                    mDb.insert(ItemContract.ItemEntry.TABLE_NAME, null, cv);
                } else {
                    Log.d(TAG, "The item is already in the db. Should update");

                    cursor.moveToFirst();
                    int quantityIntex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_QUANTITY);
                    int oldQuantity = cursor.getInt(quantityIntex);

                    ContentValues cv = new ContentValues();
                    cv.put(ItemContract.ItemEntry.COLUMN_QUANTITY, oldQuantity + quantity);

                    mDb.update(
                            ItemContract.ItemEntry.TABLE_NAME,
                            cv,
                            ItemContract.ItemEntry._ID + "=?",
                            new String[] {String.valueOf(item.getId())}
                    );
                }

                Intent intent = new Intent(mContext, OwnedItemsActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "Error!");
                t.printStackTrace();
                Toast.makeText(mContext, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item_view, parent, false);
        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {
        Item item = mListData.get(position);

        holder.mName.setText(item.getName());
        holder.mQuantity.setText(String.valueOf(item.getQuantity()));
        holder.mType.setText(item.getType());
        holder.mStatus.setText(item.getStatus());
        holder.mId.setText(String.valueOf(item.getId()));

        holder.itemView.setTag(item.getId());
    }

    @Override
    public int getItemCount() {
        if (mListData == null)
            return 0;
        return mListData.size();
    }

    public void setData(List<Item> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}

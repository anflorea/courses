package com.example.flo.gamestoreapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flo.gamestoreapp.R;
import com.example.flo.gamestoreapp.data.ItemContract;

/**
 * Created by flo on 01/02/2018.
 */

public class OwnedItemAdapter extends RecyclerView.Adapter<OwnedItemAdapter.OwnedItemViewHolder> {

    Cursor mCursor;
    Context mContext;

    public OwnedItemAdapter(Context context, Cursor cursor) {
        mCursor = cursor;
        mContext = context;
    }

    @Override
    public OwnedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.owned_item_view, parent, false);
        return new OwnedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OwnedItemViewHolder holder, int position) {
        mCursor.moveToPosition(holder.getAdapterPosition());

        int idIndex = mCursor.getColumnIndex(ItemContract.ItemEntry._ID);
        int nameIndex = mCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME);
        int quantityIndex = mCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_QUANTITY);
        int typeIndex = mCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_TYPE);

        holder.mName.setText(mCursor.getString(nameIndex));
        holder.mQuantity.setText(String.valueOf(mCursor.getInt(quantityIndex)));
        holder.mType.setText(mCursor.getString(typeIndex));

        holder.itemView.setTag(mCursor.getInt(idIndex));
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null)
            mCursor.close();
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    public class OwnedItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mQuantity;
        private TextView mType;

        public OwnedItemViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mQuantity = (TextView) itemView.findViewById(R.id.quantity);
            mType = (TextView) itemView.findViewById(R.id.type);
        }
    }
}

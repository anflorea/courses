package com.example.flo.gamestoreapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.flo.gamestoreapp.adapters.OwnedItemAdapter;
import com.example.flo.gamestoreapp.data.ItemContract;
import com.example.flo.gamestoreapp.data.ItemDbHelper;

public class OwnedItemsActivity extends AppCompatActivity {
    public static final String TAG = OwnedItemsActivity.class.getSimpleName();

    private SQLiteDatabase mDb;

    private RecyclerView mRecyclerView;

    OwnedItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned_items);

        mRecyclerView = (RecyclerView) findViewById(R.id.owned_items_rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new OwnedItemAdapter(this, null);

        mRecyclerView.setAdapter(mAdapter);

        ItemDbHelper dbHelper = new ItemDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();

        makeQuery();
    }

    private void makeQuery() {
        Cursor cursor = mDb.query(
                ItemContract.ItemEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        mAdapter.swapCursor(cursor);


    }
}

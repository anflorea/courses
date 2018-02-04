package com.example.flo.gamestoreapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by flo on 30/01/2018.
 */

public class ItemDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "itemsDb.db";

    private static final int VERSION = 1;

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_QUERY = "CREATE TABLE " + ItemContract.ItemEntry.TABLE_NAME + " (" +
                ItemContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                ItemContract.ItemEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_STATUS + " TEXT NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_RENTED + " INTEGER DEFAULT 0);";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemContract.ItemEntry.TABLE_NAME);
        onCreate(db);
    }
}

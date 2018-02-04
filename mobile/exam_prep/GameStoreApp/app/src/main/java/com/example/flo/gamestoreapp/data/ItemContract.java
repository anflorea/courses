package com.example.flo.gamestoreapp.data;

import android.provider.BaseColumns;

/**
 * Created by flo on 30/01/2018.
 */

public class ItemContract {

    public static final class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_RENTED = "rented";
    }
}

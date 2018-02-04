package com.example.flo.gamestoreapp.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by flo on 30/01/2018.
 */

public class GsonComponent {
    private static GsonComponent instance;
    private Gson mGson;

    private GsonComponent() {
        mGson = new GsonBuilder().create();
    }

    public static synchronized GsonComponent getInstance() {
        if (instance == null) {
            instance = new GsonComponent();
        }
        return instance;
    }

    public Gson getGson() {
        return mGson;
    }
}

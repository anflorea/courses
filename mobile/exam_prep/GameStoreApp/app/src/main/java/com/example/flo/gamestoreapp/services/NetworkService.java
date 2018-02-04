package com.example.flo.gamestoreapp.services;

import com.example.flo.gamestoreapp.domain.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by flo on 31/01/2018.
 */

public class NetworkService {

    public static final String BASE_MAIN_URL = "http://192.168.0.103";
    public static final int BASE_URL_PORT = 4001;
    public static final String BASE_URL = BASE_MAIN_URL + ":" + String.valueOf(BASE_URL_PORT);

    private static Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static NetworkServiceInterface mInstance = null;

    public static NetworkServiceInterface getInstance() {
        if (mInstance == null) {
            mInstance = mRetrofit.create(NetworkServiceInterface.class);
        }
        return mInstance;
    }

    public interface NetworkServiceInterface {
        @GET("/all")
        Call<List<Item>> getAllGames();

        @GET("/games")
        Call<List<Item>> getGamesForClient();

        @POST("/addGame")
        Call<Item> addItem(@Body Item item);

        @POST("/removeGame")
        Call<Item> removeItem(@Body Item item);

        @POST("/updateGame")
        Call<Item> updateItem(@Body Item item);

        @POST("/buyGame")
        Call<Item> buyItem(@Body Item item);
    }
}

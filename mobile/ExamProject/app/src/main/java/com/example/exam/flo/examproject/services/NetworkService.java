package com.example.exam.flo.examproject.services;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by flo on 01/02/2018.
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

    }
}

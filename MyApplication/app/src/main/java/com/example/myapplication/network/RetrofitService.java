package com.example.myapplication.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "http://172.19.240.1:8081/";
    private static RetrofitService mInstance;
    private final Retrofit retrofit;

    private RetrofitService() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create(new Gson())
                )
                .build();
    }

    public static synchronized RetrofitService getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitService();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


}

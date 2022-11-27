package com.example.myapplication.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "http://192.168.1.236:8081/";
    private static RetrofitService mInstance;
    private final Retrofit retrofit;

    private RetrofitService() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
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
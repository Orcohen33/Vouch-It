package com.example.myapplication.network;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String BASE_URL = "http://172.27.176.1:8081/";
    private static RetrofitManager mInstance;
    private final Retrofit retrofit;
    private OkHttpClient client;

    private RetrofitManager(OkHttpClient client) {
        this.client = client;
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(
                        GsonConverterFactory.create(new Gson())
                )
                .build();
    }

    public static synchronized RetrofitManager getInstance(OkHttpClient client) {
        if (mInstance == null) {
            mInstance = new RetrofitManager(client);
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

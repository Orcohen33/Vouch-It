package com.example.myapplication.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AuthManager {
    private static final String JWT_TOKEN = "jwt_token";
    private static AuthManager instance;
    private final OkHttpClient client;
    private final SharedPreferences sharedPreferences;

    private AuthManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();

                    Request modifiedRequest = originalRequest.newBuilder()
                            .header("Content-Type", "application/json")
                            .build();

                    return chain.proceed(modifiedRequest);
                })
                .build();
    }

    public static AuthManager getInstance(Context context) {
        if (instance == null) {
            instance = new AuthManager(context);
        }
        return instance;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setJwtToken(String jwtToken) {
        sharedPreferences.edit().putString(JWT_TOKEN, jwtToken).apply();
    }

    public String getJwtToken() {
        return sharedPreferences.getString(JWT_TOKEN, null);
    }
}

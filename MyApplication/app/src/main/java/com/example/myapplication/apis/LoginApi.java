package com.example.myapplication.apis;

import com.example.myapplication.models.user.LoginRequest;
import com.example.myapplication.models.user.RegisterRequest;
import com.example.myapplication.models.user.UserDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("/api/v1/auth/authenticate")
    Call<UserDetails> authenticate(@Body LoginRequest loginRequest);

    @POST("/api/v1/auth/refresh")
    Call<UserDetails> refresh(@Body LoginRequest loginRequest);

    @POST("/api/v1/auth/logout")
    Call<Void> logout(@Body LoginRequest loginRequest);

    @POST("/api/v1/auth/register")
    Call<UserDetails> register(@Body RegisterRequest registerRequest);
}

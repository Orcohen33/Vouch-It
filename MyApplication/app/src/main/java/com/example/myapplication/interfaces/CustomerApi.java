package com.example.myapplication.interfaces;

import com.example.myapplication.models.customer.Customer;
import com.example.myapplication.models.customer.CustomerSignin;
import com.example.myapplication.models.customer.CustomerSignup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerApi {

    @POST("/api/v1/customer/login")
    Call<Customer> login(@Body CustomerSignin customerSignin);

    @POST("/api/v1/customer/signup")
    Call<Customer> signup(@Body CustomerSignup customerSignup);


}
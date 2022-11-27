package com.example.myapplication.retrofit;

import com.example.myapplication.customer.Customer;
import com.example.myapplication.customer.CustomerRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerApi {

    @POST("/api/v1/customer/login")
    Call<Customer> login(@Body CustomerRequest customerRequest);
}

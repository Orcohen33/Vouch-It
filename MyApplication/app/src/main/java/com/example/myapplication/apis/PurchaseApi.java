package com.example.myapplication.apis;

import com.example.myapplication.models.purchase.PurchaseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PurchaseApi {

    @POST("/api/v1/purchase")
    Call<Void> createPurchase(@Body PurchaseDto purchaseDto);

}

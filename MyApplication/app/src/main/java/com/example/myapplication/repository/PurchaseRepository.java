package com.example.myapplication.repository;

import com.example.myapplication.interfaces.PurchaseApi;
import com.example.myapplication.models.purchase.PurchaseDto;
import com.example.myapplication.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseRepository {


    private PurchaseApi purchaseApi;

    public PurchaseRepository() {
        purchaseApi = RetrofitService.getInstance().getRetrofit().create(PurchaseApi.class);
    }
    /*
     This method is used to create a purchase
     */
    public void createPurchase(PurchaseDto purchaseDto) {
        purchaseApi.createPurchase(purchaseDto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("PurchaseRepository.createPurchase.onResponse: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("PurchaseRepository.createPurchase.onFailure: " + t.getMessage());
            }
        });
    }
}

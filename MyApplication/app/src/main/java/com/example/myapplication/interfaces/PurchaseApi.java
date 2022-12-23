package com.example.myapplication.interfaces;

import com.example.myapplication.models.purchase.PurchaseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PurchaseApi {

//    @GET("/api/v1/post/{id}")
//    Call<Set<Post>> getAllPosts(@Path("id") Long id);
//
//    @POST("/api/v1/post")
//    Call<Post> createPost(@Body PostDto postDto);
    @POST("/api/v1/purchase")
    Call<Void> createPurchase(@Body PurchaseDto purchaseDto);

}

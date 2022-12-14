package com.example.myapplication.interfaces;

import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.models.coupon.CouponResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface CouponApi {

    @GET("/api/v1/company/{id}/coupon")
    Call<List<CouponResponse>> getCouponsByCompanyId(@Path("id") Long id);

    @GET("/api/v1/coupon/category/{id}")
    Call<List<CouponResponse>> getCouponsByCategoryId(@Path("id") Long id);


    // CRUD operations
    @POST("/api/v1/coupon")
    Call<CouponResponse> createCoupon(@Body CouponRequest couponRequest);

    @GET("/api/v1/coupon/{id}")
    Call<CouponResponse> getCouponById(@Path("id") Long id);

    @PUT("/api/v1/coupon/{id}")
    Call<CouponResponse> updateCoupon(@Path("id") Long id, @Body CouponRequest couponRequest);

    @DELETE("/api/v1/coupon/{id}")
    Call<String> deleteCouponById(Long id);
}

package com.example.myapplication.interfaces;

import com.example.myapplication.models.coupon.Coupon;
import com.example.myapplication.models.coupon.CouponResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface CouponApi {

    @GET("/api/v1/company/{id}/coupon")
    Call<CouponResponses> getCouponsByCompanyId(@Path("id") Long id);
}

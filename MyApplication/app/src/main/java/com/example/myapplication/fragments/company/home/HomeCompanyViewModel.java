package com.example.myapplication.fragments.company.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.coupon.Coupon;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.models.coupon.CouponResponses;
import com.example.myapplication.network.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is the ViewModel for the HomeCompanyFragment.
 * It contains the list of coupons that are displayed in the HomeCompanyFragment.
 */
public class HomeCompanyViewModel extends ViewModel {

    private Long id;
    private String name;
    private String email;
    List<String> couponsTitles;

    public HomeCompanyViewModel() {
    }

    public void initializeCoupons() {
        couponsTitles = new ArrayList<>();
        CouponApi couponApi = RetrofitService
                .getInstance()
                .getRetrofit()
                .create(CouponApi.class);

        couponApi.getCouponsByCompanyId(id).enqueue(new Callback<CouponResponses>() {
            @Override
            public void onResponse(@NonNull Call<CouponResponses> call, Response<CouponResponses> response) {
                if (response.isSuccessful()) {
                    CouponResponses coupons = response.body();
                    if (coupons != null) {
                        for (CouponResponse coupon : coupons.getCoupons()) {
                            couponsTitles.add(coupon.getTitle());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CouponResponses> call, Throwable t) {
            }
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

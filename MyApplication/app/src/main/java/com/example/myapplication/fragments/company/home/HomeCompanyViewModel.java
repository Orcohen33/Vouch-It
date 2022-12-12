package com.example.myapplication.fragments.company.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.coupon.Coupon;
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

        couponApi.getCouponsByCompanyId(getId()).enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(@NonNull Call<List<Coupon>> call, @NonNull Response<List<Coupon>> response) {
                if (response.isSuccessful()) {
                    List<Coupon> coupons = response.body();
                    assert coupons != null;
                    for (Coupon coupon : coupons) {
                        System.out.println(coupon);
                        couponsTitles.add(coupon.getTitle());
                    }
                }

            }
            @Override
            public void onFailure(@NonNull Call<List<Coupon>> call, @NonNull Throwable t) {
                System.out.println("HomeCompanyViewModel: " + t.getMessage());
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

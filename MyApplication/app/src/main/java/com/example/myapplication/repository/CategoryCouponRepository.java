package com.example.myapplication.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCouponRepository {
    private static final String TAG = CategoryCouponRepository.class.getSimpleName();
    private CouponApi couponApi;

    public CategoryCouponRepository() {
        this.couponApi = RetrofitService.getInstance().getRetrofit().create(CouponApi.class);
    }

    public LiveData<List<CouponResponse>> getCouponsByCategoryId(Long id) {
        final MutableLiveData<List<CouponResponse>> data = new MutableLiveData<>();
        couponApi.getCouponsByCategoryId(id).enqueue(new Callback<List<CouponResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<CouponResponse>> call, @NonNull Response<List<CouponResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CouponResponse>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return data;
    }


}

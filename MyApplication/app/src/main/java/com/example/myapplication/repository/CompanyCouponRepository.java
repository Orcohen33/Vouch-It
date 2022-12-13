package com.example.myapplication.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyCouponRepository {
    private static final String TAG = CompanyCouponRepository.class.getSimpleName();
    private final CouponApi couponApi;

    public CompanyCouponRepository() {
        this.couponApi = RetrofitService.getInstance().getRetrofit().create(CouponApi.class);
    }

    public LiveData<List<CouponResponse>> getCouponsByCompanyId(Long id) {
        final MutableLiveData<List<CouponResponse>> data = new MutableLiveData<>();
        couponApi.getCouponsByCompanyId(id).enqueue(new Callback<List<CouponResponse>>() {
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

    public LiveData<CouponResponse> createCoupon(CouponRequest couponRequest) {
        final MutableLiveData<CouponResponse> data = new MutableLiveData<>();
        couponApi.createCoupon(couponRequest).enqueue(new Callback<CouponResponse>() {
            @Override
            public void onResponse(@NonNull Call<CouponResponse> call, @NonNull Response<CouponResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CouponResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        return data;
    }
}

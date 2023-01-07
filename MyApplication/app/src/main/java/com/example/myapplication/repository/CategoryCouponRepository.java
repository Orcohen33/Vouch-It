package com.example.myapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.apis.CouponApi;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.network.AuthManager;
import com.example.myapplication.network.RetrofitManager;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCouponRepository {
    private static final String TAG = CategoryCouponRepository.class.getSimpleName();
    private CouponApi couponApi;
    private AuthManager authManager;

    public CategoryCouponRepository(Context context) {
        this.couponApi = RetrofitManager.getInstance(new OkHttpClient()).getRetrofit().create(CouponApi.class);
        this.authManager = AuthManager.getInstance(context);
    }

    public LiveData<List<CouponResponse>> getCouponsByCategoryId(Long id) {
        final MutableLiveData<List<CouponResponse>> data = new MutableLiveData<>();
        couponApi.getCouponsByCategoryId(id).enqueue(new Callback<>() {
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

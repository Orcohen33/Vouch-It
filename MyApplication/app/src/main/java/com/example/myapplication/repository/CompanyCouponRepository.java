package com.example.myapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.apis.CouponApi;
import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.network.AuthManager;
import com.example.myapplication.network.RetrofitManager;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyCouponRepository {
    private static final String TAG = CompanyCouponRepository.class.getSimpleName();
    private AuthManager authManager;
    private final CouponApi couponApi;

    public CompanyCouponRepository(Context context) {
        this.couponApi = RetrofitManager.getInstance(new OkHttpClient()).getRetrofit().create(CouponApi.class);
        this.authManager = AuthManager.getInstance(context);
    }


    public LiveData<List<CouponResponse>> getCouponsByCompanyId(Long id){
        final MutableLiveData<List<CouponResponse>> data = new MutableLiveData<>();
        new Thread(() -> {
            try{
                Call<List<CouponResponse>> call = couponApi.getCouponsByCompanyId(id, "Bearer " + authManager.getJwtToken());
                Response<List<CouponResponse>> response = call.execute();
                if(response.isSuccessful()){
                    data.postValue(response.body());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return data;
    }
//    public LiveData<List<CouponResponse>> getCouponsByCompanyId(Long id) {
//        System.out.println("CompanyCouponRepository.getCouponsByCompanyId");
//        final MutableLiveData<List<CouponResponse>> data = new MutableLiveData<>();
//        couponApi.getCouponsByCompanyId(id).enqueue(new Callback<List<CouponResponse>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<CouponResponse>> call, @NonNull Response<List<CouponResponse>> response) {
//                if (response.isSuccessful()) {
//                    data.setValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<CouponResponse>> call, @NonNull Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//            }
//        });
//        // sort data before returning it
//        return data;
//    }

    public void createCoupon(CouponRequest couponRequest) {
        final MutableLiveData<CouponResponse> data = new MutableLiveData<>();
        couponApi.createCoupon(couponRequest, "Bearer " + authManager.getJwtToken()).enqueue(new Callback<CouponResponse>() {
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
    }

    public void updateCoupon(Long id ,CouponRequest couponRequest) {
        final MutableLiveData<CouponResponse> data = new MutableLiveData<>();
        couponApi.updateCoupon(id, couponRequest, "Bearer " + authManager.getJwtToken()).enqueue(new Callback<CouponResponse>() {
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
    }
    public void deleteCouponById(Long id) {
        couponApi.deleteCouponById(id, "Bearer " + authManager.getJwtToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("Coupon deleted successfully");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}

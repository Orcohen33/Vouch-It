package com.example.myapplication.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.category.CategoryResponse;
import com.example.myapplication.models.company.CompanyResponse;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponRepository {

    private static final String TAG = CompanyCouponRepository.class.getSimpleName();
    private final CouponApi couponApi;

    public CouponRepository() {
        this.couponApi = RetrofitService.getInstance().getRetrofit().create(CouponApi.class);
    }

    public LiveData<CouponResponse> getCouponById(Long id) {
        final MutableLiveData<CouponResponse> data = new MutableLiveData<>();
        couponApi.getCouponById(id).enqueue(new Callback<CouponResponse>() {
            @Override
            public void onResponse(@NonNull Call<CouponResponse> call, @NonNull Response<CouponResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());

                    String couponTitle = response.body().getTitle();
                    String couponDescription = response.body().getDescription();
                    Double couponPrice = response.body().getPrice();
                    Integer couponAmount = response.body().getAmount();
                    String startDate = response.body().getStartDate();
                    String endDate = response.body().getEndDate();
                    CategoryResponse category = response.body().getCategory();
                    CompanyResponse company = response.body().getCompany();
                    
                    CouponResponse couponResponse = new CouponResponse();
                    couponResponse.setTitle(couponTitle);
                    couponResponse.setDescription(couponDescription);
                    couponResponse.setPrice(couponPrice);
                    couponResponse.setAmount(couponAmount);
                    couponResponse.setStartDate(startDate);
                    couponResponse.setEndDate(endDate);
                    couponResponse.setCategory(category);
                    couponResponse.setCompany(company);
                    couponResponse.setId(id);
                    data.setValue(couponResponse);

                }
            }
            @Override
            public void onFailure(@NonNull Call<CouponResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
        return data;
    }
}

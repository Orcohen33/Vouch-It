package com.example.myapplication.fragments.customer.mycoupons;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyCouponsViewModel extends ViewModel {

    List<String> couponsTitles;
    List<String> couponsCode;
    List<Integer> couponsImage;
    List<String> couponsEndDate;

    private CompanyCouponRepository repository;
    private LiveData<List<CouponResponse>> couponResponsesLiveData;

    public MyCouponsViewModel(){
        couponsTitles = new ArrayList<>();
        couponsCode = new ArrayList<>();
        couponsImage = new ArrayList<>();
        couponsEndDate = new ArrayList<>();
//        repository = new CompanyCouponRepository();
    }

    public void init(){
        couponResponsesLiveData = repository.getCouponsByCompanyId(1L);
    }
    public LiveData<List<CouponResponse>> getCouponResponsesLiveData() {
        System.out.println("HomeCompanyViewModel: getCouponResponsesLiveData");
        // sort the data before returning it
        couponResponsesLiveData = Transformations.map(couponResponsesLiveData, couponResponses -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                couponResponses.sort(Comparator.comparing(CouponResponse::getTitle));
            }
            return couponResponses;
        });
        return couponResponsesLiveData;
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public void setCouponsTitles(List<String> couponsTitles) {
        this.couponsTitles = couponsTitles;
    }

    public List<String> getCouponsCode() {
        return couponsCode;
    }

    public void setCouponsCode(List<String> couponsCode) {
        this.couponsCode = couponsCode;
    }

    public List<Integer> getCouponImage() {
        return couponsImage;
    }

    public void setCouponImage(List<Integer> couponImage) {
        this.couponsImage = couponImage;
    }

    public List<String> getCouponsEndDate() {
        return couponsEndDate;
    }

    public void setCouponsEndDate(List<String> couponsEndDate) {
        this.couponsEndDate = couponsEndDate;
    }




}
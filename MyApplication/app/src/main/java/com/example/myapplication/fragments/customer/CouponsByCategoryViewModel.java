package com.example.myapplication.fragments.customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CategoryCouponRepository;

import java.util.ArrayList;
import java.util.List;

public class CouponsByCategoryViewModel extends ViewModel {

    List<Integer> couponsImages;
    List<String> couponsTitles;

    List<String> couponsPrices;

    private CategoryCouponRepository categoryCouponRepository;
    private LiveData<List<CouponResponse>> categoryCoupons;


    public CouponsByCategoryViewModel() {
        couponsImages = new ArrayList<>();
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();
    }

    public void init(Long id){
        if (categoryCoupons ==null && id!=null){
            categoryCouponRepository = new CategoryCouponRepository();
            categoryCoupons = categoryCouponRepository.getCouponsByCategoryId(id);
        }
    }

    public LiveData<List<CouponResponse>> getCategoryCouponsResponseLiveData() {
        return categoryCoupons;
    }


    public List<Integer> getCouponsImages() {
        return couponsImages;
    }

    public void setCouponsImages(List<Integer> couponsImages) {
        this.couponsImages = couponsImages;
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public void setCouponsTitles(List<String> couponsTitles) {
        this.couponsTitles = couponsTitles;
    }

    public List<String> getCouponsPrices() {
        return couponsPrices;
    }

    public void setCouponsPrices(List<String> couponsPrices) {
        this.couponsPrices = couponsPrices;
    }

}

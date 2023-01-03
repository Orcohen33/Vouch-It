package com.example.myapplication.fragments.customer.mycoupons;

import androidx.lifecycle.ViewModel;


import java.util.ArrayList;
import java.util.List;

public class MyCouponsViewModel extends ViewModel {

    List<String> couponsTitles;
    List<Long> couponsCode;
    List<Integer> couponsImage;
    List<String> couponsEndDate;

    public MyCouponsViewModel(){
        couponsTitles = new ArrayList<>();
        couponsCode = new ArrayList<>();
        couponsImage = new ArrayList<>();
        couponsEndDate = new ArrayList<>();
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public void setCouponsTitles(List<String> couponsTitles) {
        this.couponsTitles = couponsTitles;
    }

    public List<Long> getCouponsCode() {
        return couponsCode;
    }

    public void setCouponsCode(List<Long> couponsCode) {
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
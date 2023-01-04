package com.example.myapplication.fragments.customer.receipt;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponShared;

import java.util.ArrayList;
import java.util.List;

public class ReceiptViewModel extends ViewModel {
    List<String> couponsTitles;
    List<Long> couponsIds;
    List<String> couponsPrices;

    List<CouponShared> mDetails;

    public ReceiptViewModel() {
        couponsTitles = new ArrayList<>();
        couponsIds = new ArrayList<>();
        couponsPrices = new ArrayList<>();
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public List<Long> getCouponsIds() {return couponsIds;}

    public List<String> getCouponsPrices() {
        return couponsPrices;
    }

    public void setCouponShareds(List<CouponShared> couponShareds){
        this.mDetails = couponShareds;
        couponsTitles.clear();
        couponsIds.clear();
        couponsPrices.clear();
        for (CouponShared couponShared : couponShareds) {
            couponsTitles.add(couponShared.getTitle());
            couponsIds.add(couponShared.getId());
            couponsPrices.add(couponShared.getPrice() + "");
        }
    }



    // TODO: Implement the ViewModel
}
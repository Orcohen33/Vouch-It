package com.example.myapplication.fragments.customer.cart;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel{

    List<String> couponsTitles;

    List<String> couponsPrices;
    List<Long> couponsIds;

    List<CouponShared> mDetails;

    public CartViewModel() {
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();
        couponsIds = new ArrayList<>();
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public List<String> getCouponsPrices() {
        return couponsPrices;
    }

    public void setCouponShareds(List<CouponShared> couponShareds) {
        this.mDetails = couponShareds;
        for (CouponShared couponShared : couponShareds) {
            couponsTitles.add(couponShared.getTitle());
            couponsPrices.add(couponShared.getPrice() + "");
            couponsIds.add(couponShared.getId());
        }
    }


    public String getTotalPrice() {
        int totalPrice = 0;
        for (CouponShared couponShared : mDetails) {
            totalPrice += Double.parseDouble(couponShared.getPrice());
        }
        return "סה\"כ: "+totalPrice + "₪";
    }

    public void updateTotalPrice() {
        int totalPrice = 0;
        for (CouponShared couponShared : mDetails) {
            totalPrice += Double.parseDouble(couponShared.getPrice());
        }
    }
}

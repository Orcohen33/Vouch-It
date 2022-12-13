package com.example.myapplication.fragments.customer.shows;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ShowViewModel extends ViewModel {

    List<Integer> couponsImages;
    List<String> couponsTitles;
    List<String> couponsPrices;

    public ShowViewModel() {
        initializeCoupons();
    }

    private void initializeCoupons() {
        couponsImages = new ArrayList<>();
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();

        couponsTitles.add("CouponRequest 1");
        couponsTitles.add("CouponRequest 2");
        couponsTitles.add("CouponRequest 3");
        couponsTitles.add("CouponRequest 4");
        couponsTitles.add("CouponRequest 5");

        couponsPrices.add("Price :1$");
        couponsPrices.add("Price :2$");
        couponsPrices.add("Price :3$");
        couponsPrices.add("Price :4$");
        couponsPrices.add("Price :5$");

        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);

    }

}
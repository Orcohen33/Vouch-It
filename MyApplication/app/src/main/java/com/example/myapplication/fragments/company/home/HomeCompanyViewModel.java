package com.example.myapplication.fragments.company.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeCompanyViewModel extends ViewModel {

    List<String> couponsTitles;

    public HomeCompanyViewModel() {
        initializeCoupons();
    }

    private void initializeCoupons() {
        couponsTitles = new ArrayList<>();
        couponsTitles.add("Coupon 1");
        couponsTitles.add("Coupon 2");
        couponsTitles.add("Coupon 3");
        couponsTitles.add("Coupon 4");
        couponsTitles.add("Coupon 5");
    }

}

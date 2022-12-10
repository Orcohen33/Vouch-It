package com.example.myapplication.fragments.company.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the ViewModel for the HomeCompanyFragment.
 * It contains the list of coupons that are displayed in the HomeCompanyFragment.
 */
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

package com.example.myapplication.fragments.company.add_coupon;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.JsonAdapter;

import java.time.LocalDate;

/**
 * This class is the ViewModel for the AddCouponFragment.
 * Contains all the fields we need to fill in so we can post a coupon
 */
public class AddCouponViewModel extends ViewModel {

    String couponTitle;
    String couponDescription;
    String couponCategory;
    String couponPrice;
    String couponAmount;
    String couponImage;
    LocalDate couponStartDate;
    LocalDate couponEndDate;

    public AddCouponViewModel() {
        couponTitle = "";
        couponDescription = "";
        couponCategory = "";
        couponPrice = "";
        couponAmount = "";
        couponImage = "";
        couponStartDate = null;
        couponEndDate = null;
    }

    public void addCoupon() {
        System.out.println("Adding coupon");
    }

    @Override
    public String toString() {
        return "AddCouponViewModel{" +
                "couponTitle='" + couponTitle + '\'' +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponCategory='" + couponCategory + '\'' +
                ", couponPrice='" + couponPrice + '\'' +
                ", couponAmount='" + couponAmount + '\'' +
                ", couponImage='" + couponImage + '\'' +
                ", couponStartDate=" + couponStartDate +
                ", couponEndDate=" + couponEndDate +
                '}';
    }

// TODO: Implement the ViewModel
}
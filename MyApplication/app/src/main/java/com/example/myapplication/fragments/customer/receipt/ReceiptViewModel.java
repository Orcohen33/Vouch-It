package com.example.myapplication.fragments.customer.receipt;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponShared;

import java.util.ArrayList;
import java.util.List;

public class ReceiptViewModel extends ViewModel {
    List<String> couponsTitles;
    List<Long> couponsIds;

    List<CouponShared> mDetails;

    public ReceiptViewModel() {
        couponsTitles = new ArrayList<>();
        couponsIds = new ArrayList<>();
    }


    // TODO: Implement the ViewModel
}
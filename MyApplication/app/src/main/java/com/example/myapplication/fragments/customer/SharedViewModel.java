package com.example.myapplication.fragments.customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;

public class SharedViewModel extends ViewModel {
    // singleton pattern
    private static SharedViewModel instance;

    private MutableLiveData<List<CouponShared>> mCoupons = new MutableLiveData<>();

    public void setCoupons(List<CouponShared> coupons) {
        mCoupons.setValue(coupons);
    }

    public LiveData<List<CouponShared>> getCoupons() {
        return mCoupons;
    }
}

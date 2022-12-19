package com.example.myapplication.fragments.customer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;

public class SharedViewModel extends AndroidViewModel {
    // singleton pattern
    private static SharedViewModel instance;

    private MutableLiveData<List<CouponShared>> mCoupons = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
        if (instance == null) {
            instance = this;
            mCoupons = new MutableLiveData<>();
        }
    }

    public void setCoupons(List<CouponShared> coupons) {
        mCoupons.setValue(coupons);
    }

    public MutableLiveData<List<CouponShared>> getCoupons() {
        if (mCoupons == null) {
            mCoupons = new MutableLiveData<>();
        }
        return mCoupons;
    }
}

package com.example.myapplication.fragments.customer.cart;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel implements CustomerCartViewAdapter.ItemClickListener{

    List<String> couponsTitles;

    List<String> couponsPrices;
    List<Long> couponsIds;

    private List<CouponShared> mDetails;

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
    public void setSharedViewModel(SharedViewModel mSharedViewModel) {
        mSharedViewModel.getCoupons().observeForever(couponShareds -> {
            mDetails = couponShareds;
            couponsTitles.clear();
            couponsPrices.clear();
            couponsIds.clear();
            for (CouponShared couponShared : couponShareds) {
                couponsTitles.add(couponShared.getTitle());
                couponsPrices.add(couponShared.getPrice().toString());
                couponsIds.add(couponShared.getId());
            }
        });
    }

    @Override
    public void onDeleteClick(View view, int position) {

    }

    @Override
    public void onAddToCartClick(View view, int position) {

    }

}

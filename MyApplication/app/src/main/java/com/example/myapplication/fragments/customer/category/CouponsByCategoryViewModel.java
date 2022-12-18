package com.example.myapplication.fragments.customer.category;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.adapters.CustomerCouponsViewAdapter;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.models.coupon.CouponShared;
import com.example.myapplication.repository.CategoryCouponRepository;

import java.util.ArrayList;
import java.util.List;

public class CouponsByCategoryViewModel extends ViewModel implements CustomerCouponsViewAdapter.ItemClickListener {

    List<Integer> couponsImages;
    List<String> couponsTitles;

    List<String> couponsPrices;
    List<Long> couponsIds;


    private CategoryCouponRepository categoryCouponRepository;
    private LiveData<List<CouponResponse>> categoryCoupons;


    public CouponsByCategoryViewModel() {
        couponsImages = new ArrayList<>();
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();
        couponsIds = new ArrayList<>();
    }

    public void init(Long id) {
        if (categoryCoupons == null && id != null) {
            categoryCouponRepository = new CategoryCouponRepository();
            categoryCoupons = categoryCouponRepository.getCouponsByCategoryId(id);
        }
    }

    public LiveData<List<CouponResponse>> getCategoryCouponsResponseLiveData() {
        return categoryCoupons;
    }


    public List<Integer> getCouponsImages() {
        return couponsImages;
    }

    public void setCouponsImages(List<Integer> couponsImages) {
        this.couponsImages = couponsImages;
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public void setCouponsTitles(List<String> couponsTitles) {
        this.couponsTitles = couponsTitles;
    }

    public List<String> getCouponsPrices() {
        return couponsPrices;
    }

    public void setCouponsPrices(List<String> couponsPrices) {
        this.couponsPrices = couponsPrices;
    }

    public List<Long> getCouponsIds() {
        return couponsIds;
    }

    public void setCouponsIds(List<Long> couponsIds) {
        this.couponsIds = couponsIds;
    }


    @Override
    public void onAddToCartClick(View view, int position) {

        System.out.println("couponId: " + couponsIds.get(position));
        Toast.makeText(view.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
    }


}

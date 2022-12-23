package com.example.myapplication.fragments.customer.cart;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.coupon.CouponShared;
import com.example.myapplication.models.purchase.PurchaseDto;
import com.example.myapplication.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel{


    List<String> couponsPrices;
    List<String> couponsTitles;
    List<Long> couponsIds;

    List<CouponShared> mDetails;


    private PurchaseRepository purchaseRepository;


    public CartViewModel() {
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();
        couponsIds = new ArrayList<>();
        purchaseRepository = new PurchaseRepository();
    }

    public List<String> getCouponsTitles() {
        return couponsTitles;
    }

    public List<String> getCouponsPrices() {
        return couponsPrices;
    }

    public List<Long> getCouponsIds() {return couponsIds;}

    public void setCouponShareds(List<CouponShared> couponShareds) {
        this.mDetails = couponShareds;
        couponsTitles.clear();
        couponsPrices.clear();
        couponsIds.clear();
        for (CouponShared couponShared : couponShareds) {
            couponsTitles.add(couponShared.getTitle());
            couponsPrices.add(couponShared.getPrice() + "");
            couponsIds.add(couponShared.getId());
        }
    }


    public String getTotalPrice() {
        double totalPrice = 0;
        for (CouponShared couponShared : mDetails) {
            totalPrice += Double.parseDouble(couponShared.getPrice());
        }
        return totalPrice + "";
    }

    public String getTotalPriceFormat() {
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

    public void purchaseCoupons(PurchaseDto purchaseDto) {
        purchaseRepository.createPurchase(purchaseDto);
    }
}

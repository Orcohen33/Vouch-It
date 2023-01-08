package com.example.myapplication.fragments.customer.cardpayment;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.purchase.PurchaseDto;
import com.example.myapplication.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

public class PaymentViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private double totalPrice;
    private List<Long> couponIds;
    private Long customerId;
    private PurchaseRepository purchaseRepository;

    public PaymentViewModel() {
        purchaseRepository = new PurchaseRepository();
        customerId = 0L;
        couponIds = new ArrayList<>();
        totalPrice = 0;
    }


    public void purchaseCoupons(PurchaseDto purchaseDto) {
        purchaseRepository.createPurchase(purchaseDto);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PaymentViewModel setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<Long> getCouponIds() {
        return couponIds;
    }

    public PaymentViewModel setCouponIds(List<Long> couponIds) {
        this.couponIds = couponIds;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public PaymentViewModel setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

}
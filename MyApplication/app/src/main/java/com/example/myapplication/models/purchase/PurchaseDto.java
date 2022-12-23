package com.example.myapplication.models.purchase;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDto {

    private Long customerId;
    private List<Long> couponIds;
    private String totalPrice;

    public PurchaseDto() {
    }

    public PurchaseDto(Long customerId, List<Long> couponIds, String totalPrice) {
        this.customerId = customerId;
        this.couponIds = couponIds;
        this.totalPrice = totalPrice;

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(List<Long> couponIds) {
        this.couponIds = couponIds;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}

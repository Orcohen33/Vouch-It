package com.example.myapplication.fragments.customer.shopping;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class ShoppingViewModel extends CouponsByCategoryViewModel {

    Long categoryId;
    public ShoppingViewModel() {
        super();
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

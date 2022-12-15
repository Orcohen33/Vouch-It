package com.example.myapplication.fragments.customer.spa;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class SpaViewModel extends CouponsByCategoryViewModel {

    Long categoryId;
    public SpaViewModel() {
        super();
        super.init(categoryId);
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
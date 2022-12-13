package com.example.myapplication.fragments.customer.shows;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class ShowsViewModel extends CouponsByCategoryViewModel {

    Long categoryId;
    public ShowsViewModel() {
        super();
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
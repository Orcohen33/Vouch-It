package com.example.myapplication.fragments.customer.restaurants;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class RestaurantsViewModel extends CouponsByCategoryViewModel {

    Long categoryId;
    public RestaurantsViewModel() {
        super();
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
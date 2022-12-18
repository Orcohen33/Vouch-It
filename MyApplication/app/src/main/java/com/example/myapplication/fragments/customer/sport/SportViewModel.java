package com.example.myapplication.fragments.customer.sport;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class SportViewModel extends CouponsByCategoryViewModel {

    Long categoryId;
    public SportViewModel() {
        super();
        super.init(categoryId);
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

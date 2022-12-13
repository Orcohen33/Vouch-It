package com.example.myapplication.fragments.customer.attractions;

import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class AttractionsViewModel extends CouponsByCategoryViewModel {

        Long categoryId;

        public AttractionsViewModel() {
            super();
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }


}

package com.example.myapplication.fragments.company.add_coupon;

import android.view.View;
import android.widget.AdapterView;

public class AddCouponSpinnerListener implements AdapterView.OnItemSelectedListener {
    private final AddCouponViewModel mViewModel;
    public AddCouponSpinnerListener(AddCouponViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mViewModel.setCouponCategoryId(id + 1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mViewModel.setCouponCategoryId(1L);
    }
}

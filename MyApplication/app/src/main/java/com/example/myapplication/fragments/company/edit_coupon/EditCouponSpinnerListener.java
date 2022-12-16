package com.example.myapplication.fragments.company.edit_coupon;

import android.view.View;
import android.widget.AdapterView;

public class EditCouponSpinnerListener implements AdapterView.OnItemSelectedListener {
    private final EditCouponViewModel mViewModel;
    public EditCouponSpinnerListener(EditCouponViewModel mViewModel) {
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

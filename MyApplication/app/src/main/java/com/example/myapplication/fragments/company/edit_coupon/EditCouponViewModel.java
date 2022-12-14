package com.example.myapplication.fragments.company.edit_coupon;

import android.app.Application;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.databinding.FragmentEditCouponCompanyBinding;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;
import com.example.myapplication.repository.CouponRepository;

/**
 * This class is the ViewModel for the EditCouponFragment.
 * It contains the coupon that is displayed in the EditCouponFragment.
 */
public class EditCouponViewModel extends AndroidViewModel {

    CouponRepository couponRepository;
    LiveData<CouponResponse> couponResponse;

    public EditCouponViewModel(@NonNull Application application) {
        super(application);
        couponRepository = new CouponRepository();
    }

    public void init(Long couponId) {
        couponResponse = couponRepository.getCouponById(couponId);
    }

    public LiveData<CouponResponse> getCouponResponse() {
        return couponResponse;
    }

    public void updateCoupon(){
        // TODO: Implement this method
    }
//    public void updateCoupon(Long couponId, String title, String description, String category, String image, String startDate, String endDate, String price, String amount) {
//        couponRepository.updateCoupon(couponId, title, description, category, image, startDate, endDate, price, amount);
//    }

}

package com.example.myapplication.fragments.company.edit_coupon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;
import com.example.myapplication.repository.CouponRepository;

/**
 * This class is the ViewModel for the EditCouponFragment.
 * It contains the coupon that is displayed in the EditCouponFragment.
 */
public class EditCouponViewModel extends AndroidViewModel {

    Long companyId;

    String companyName;

    String companyEmail;
    String couponTitle;
    String couponDescription;
    Long couponCategoryId;
    String couponPrice;
    String couponAmount;
    String couponImage;
    String couponStartDate;
    String couponEndDate;
    CouponRepository couponRepository;
    CompanyCouponRepository companyCouponRepository;
    LiveData<CouponResponse> couponResponse;

    public EditCouponViewModel(@NonNull Application application) {
        super(application);
        couponTitle = "";
        couponDescription = "";
        couponCategoryId = null;
        couponPrice = "";
        couponAmount = "";
        couponImage = "";
        couponStartDate = null;
        couponEndDate = null;
        couponRepository = new CouponRepository();
        companyCouponRepository = new CompanyCouponRepository();
    }

    public void getCouponById(Long couponId) {
        couponResponse = couponRepository.getCouponById(couponId);
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setCouponCategoryId(Long couponCategoryId) {
        this.couponCategoryId = couponCategoryId;
    }

    public LiveData<CouponResponse> getCouponResponse() {
        return couponResponse;
    }

    public void updateCoupon(Long couponId){
        CouponRequest coupon = new CouponRequest();
        coupon.setTitle(couponTitle);
        coupon.setDescription(couponDescription);
        coupon.setPrice(Double.valueOf(couponPrice));
        coupon.setAmount(Integer.valueOf(couponAmount));
        coupon.setImage(couponImage);
        coupon.setStartDate(couponStartDate);
        coupon.setEndDate(couponEndDate);
        coupon.setCompanyId(companyId);
        coupon.setCategoryId(couponCategoryId);
        companyCouponRepository.updateCoupon(couponId, coupon);
    }
    public void setArgs(String... args) {
        couponTitle = args[0];
        couponDescription = args[1];
        couponPrice = args[2];
        couponAmount = args[3];
        couponImage = args[4];
        couponStartDate = args[5];
        couponEndDate = args[6];
    }

}

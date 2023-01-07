package com.example.myapplication.fragments.company.add_or_edit_coupon;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;
import com.example.myapplication.repository.CouponRepository;

/**
 * This class is the ViewModel for the AddOrEditCouponFragment.
 * Contains all the fields we need to fill in so we can post a coupon
 */
public class AddOrEditCouponViewModel extends AndroidViewModel {

    Long companyId;

    String companyName;

    String companyEmail;

    String couponTitle;
    String couponDescription;
    Long couponCategoryId;
    String couponPrice;
    String couponAmount;
    byte[] couponImage;
    String couponStartDate;
    String couponEndDate;
    CouponRepository couponRepository;
    CompanyCouponRepository companyCouponRepository;
    LiveData<CouponResponse> couponResponse;
    public AddOrEditCouponViewModel(Application application) {
        super(application);
        couponTitle = "";
        couponDescription = "";
        couponCategoryId = null;
        couponPrice = "";
        couponAmount = "";
        couponImage = null;
        couponStartDate = null;
        couponEndDate = null;
        couponRepository = new CouponRepository();
        companyCouponRepository = new CompanyCouponRepository(application);
    }

    public void addCoupon() {
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
        companyCouponRepository.createCoupon(coupon);
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
        couponStartDate = args[4];
        couponEndDate = args[5];
    }

    public void getCouponById(Long couponId) {
        couponResponse = couponRepository.getCouponById(couponId);
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCouponCategoryId(Long couponCategoryId) {
        this.couponCategoryId = couponCategoryId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public LiveData<CouponResponse> getCouponResponse() {
        return couponResponse;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    public void setCouponImage(byte[] couponImage) {
        this.couponImage = couponImage;
    }

    @Override
    public String toString() {
        return "AddOrEditCouponViewModel{" +
                "couponTitle='" + couponTitle + '\'' +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponCategory='" + couponCategoryId + '\'' +
                ", couponPrice='" + couponPrice + '\'' +
                ", couponAmount='" + couponAmount + '\'' +
                ", couponImage='" + couponImage + '\'' +
                ", couponStartDate=" + couponStartDate +
                ", couponEndDate=" + couponEndDate +
                '}';
    }
}
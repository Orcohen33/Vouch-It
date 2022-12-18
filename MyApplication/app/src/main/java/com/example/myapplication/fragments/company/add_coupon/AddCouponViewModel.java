package com.example.myapplication.fragments.company.add_coupon;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.repository.CompanyCouponRepository;

/**
 * This class is the ViewModel for the AddCouponFragment.
 * Contains all the fields we need to fill in so we can post a coupon
 */
public class AddCouponViewModel extends AndroidViewModel {

    public Long getCompanyId() {
        return companyId;
    }

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
    CompanyCouponRepository companyCouponRepository;

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setCouponCategoryId(Long couponCategoryId) {
        this.couponCategoryId = couponCategoryId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public AddCouponViewModel(Application application) {
        super(application);
        couponTitle = "";
        couponDescription = "";
        couponCategoryId = null;
        couponPrice = "";
        couponAmount = "";
        couponImage = null;
        couponStartDate = null;
        couponEndDate = null;
        companyCouponRepository = new CompanyCouponRepository();
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

    public void setCouponImage(byte[] couponImage) {
        this.couponImage = couponImage;
    }
    public void setArgs(String... args) {
//        companyId = Long.valueOf(args[0]);
        couponTitle = args[0];
        couponDescription = args[1];
//        couponCategoryId = Long.valueOf(args[3]);
        couponPrice = args[2];
        couponAmount = args[3];
//        couponImage = args[4];
        couponStartDate = args[4];
        couponEndDate = args[5];
    }

    @Override
    public String toString() {
        return "AddCouponViewModel{" +
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
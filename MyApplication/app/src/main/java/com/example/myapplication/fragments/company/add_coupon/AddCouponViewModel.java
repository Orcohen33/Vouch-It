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
    String couponImage;
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
        couponImage = "";
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

    public void setArgs(String... args) {
//        companyId = Long.valueOf(args[0]);
        couponTitle = args[0];
        couponDescription = args[1];
//        couponCategoryId = Long.valueOf(args[3]);
        couponPrice = args[2];
        couponAmount = args[3];
        couponImage = args[4];
        couponStartDate = args[5];
        couponEndDate = args[6];
    }

//    public void initializeAllFields(FragmentAddCouponBinding binding) {
//        couponTitle = Objects.requireNonNull(binding.companyCouponNameInput.getText()).toString();
//        couponDescription = Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString();
//        couponCategoryId = 3L;
//        couponPrice = Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString();
//        couponAmount = Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString();
//        couponImage = null;
//        binding.companyCouponNameInput.setError("Title is required");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            int startDateMonth = binding.companyCouponStartDateInput.getMonth() + 1;
//            int endDateMonth = binding.companyCouponEndDateInput.getMonth() + 1;
//            couponStartDate =
//                    binding.companyCouponStartDateInput.getYear()+"-" +
//                    (startDateMonth < 10 ? "0"+startDateMonth: startDateMonth)+"-" +
//                    binding.companyCouponStartDateInput.getDayOfMonth();
//
//            couponEndDate = binding.companyCouponEndDateInput.getYear()+"-" +
//                              (endDateMonth < 10 ? "0"+endDateMonth: endDateMonth)+"-" +
//                              binding.companyCouponEndDateInput.getDayOfMonth();
//        }
//        System.out.println("Initializing all fields");
//    }

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
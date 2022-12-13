package com.example.myapplication.fragments.company.add_coupon;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.databinding.FragmentAddCouponBinding;
import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.repository.CompanyCouponRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
        couponCategoryId = 3L;
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
        coupon.setCategoryId(3L);
//        List<CouponRequest> coupons = generate250Coupons();
//        for (CouponRequest couponRequest : coupons) {
//            companyCouponRepository.createCoupon(couponRequest);
//        }
        companyCouponRepository.createCoupon(coupon);
    }
    List<CouponRequest> generate250Coupons(){
        Random random = new Random();
        List<CouponRequest> coupons = new ArrayList<>();
        for (int i = 0; i < 250; i++) {
            CouponRequest request = new CouponRequest();
            request.setTitle("Coupon #" + i);
            request.setDescription("Description for coupon #" + i);
            request.setStartDate("2022-12-12");
            request.setEndDate("2022-12-31");
            request.setAmount(100);
            request.setPrice(10.0);
            request.setCompanyId(5L);
            request.setCategoryId((long) (random.nextInt(5) + 1));
            coupons.add(request);
        }
        return coupons;
    }

    public void initializeAllFields(FragmentAddCouponBinding binding) {
        couponTitle = Objects.requireNonNull(binding.companyCouponNameInput.getText()).toString();
        couponDescription = Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString();
        couponCategoryId = 3L;
        couponPrice = Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString();
        couponAmount = Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString();
        couponImage = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int startDateMonth = binding.companyCouponStartDateInput.getMonth() + 1;
            int endDateMonth = binding.companyCouponEndDateInput.getMonth() + 1;
            couponStartDate =
                    binding.companyCouponStartDateInput.getYear()+"-" +
                    (startDateMonth < 10 ? "0"+startDateMonth: startDateMonth)+"-" +
                    binding.companyCouponStartDateInput.getDayOfMonth();

            couponEndDate = binding.companyCouponEndDateInput.getYear()+"-" +
                    (endDateMonth < 10 ? "0"+endDateMonth: endDateMonth)+"-" +
                    binding.companyCouponEndDateInput.getDayOfMonth();
        }
        System.out.println("Initializing all fields");
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
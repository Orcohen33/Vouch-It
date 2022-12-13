package com.example.myapplication.fragments.company.add_coupon;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.databinding.FragmentAddCouponBinding;
import com.example.myapplication.interfaces.CouponApi;
import com.example.myapplication.models.category.CategoryRequest;
import com.example.myapplication.models.company.CompanyRequest;
import com.example.myapplication.models.coupon.CouponRequest;
import com.example.myapplication.repository.CompanyCouponRepository;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.util.Objects;

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
    LocalDate couponStartDate;
    LocalDate couponEndDate;
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
        System.out.println("[ADD_COUPON]: "+coupon);
        companyCouponRepository.createCoupon(coupon);
        System.out.println("Adding coupon");
    }

    public void initializeAllFields(FragmentAddCouponBinding binding) {
        couponTitle = Objects.requireNonNull(binding.companyCouponNameInput.getText()).toString();
        couponDescription = Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString();
        couponCategoryId = 3L;
        couponPrice = Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString();
        couponAmount = Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString();
        couponImage = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            couponStartDate = LocalDate.of(
                    Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getYear())),
                    Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getMonth() + 1)),
                    Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getDayOfMonth()))
            );
            couponEndDate = LocalDate.of(
                    Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getYear())),
                    Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getMonth() + 1)),
                    Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getDayOfMonth()))
            );
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
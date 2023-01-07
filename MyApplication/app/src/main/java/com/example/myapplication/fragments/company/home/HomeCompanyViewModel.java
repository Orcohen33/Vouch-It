package com.example.myapplication.fragments.company.home;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.myapplication.models.company.CompanyDetails;
import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This code is for a ViewModel class in an Android app that is used in the HomeCompanyFragment.
 * The ViewModel contains information about a company, including its ID, name, and email, as well
 * as lists of coupon titles and coupon IDs.
 * It also has a reference to a CompanyCouponRepository object and a LiveData object containing
 * a list of CouponResponse objects.
 * The ViewModel has a method called "init" that initializes the companyCouponRepository
 * and sets the LiveData object to contain the coupons belonging to the company with the specified ID.
 * It also has a method called "getCouponResponsesLiveData" that returns the LiveData object,
 * but first sorts the CouponResponse objects contained in it by title.
 * Finally, it has a method called "deleteCouponById" that uses the companyCouponRepository
 * to delete a coupon with the specified ID.
 */
public class HomeCompanyViewModel extends AndroidViewModel {

    private Long id;
    private String name;
    private String email;

    CompanyDetails companyDetails;
    List<String> couponsTitles;



    List<Long> couponsIds;

    private CompanyCouponRepository companyCouponRepository;
    private LiveData<List<CouponResponse>> couponResponsesLiveData;

    public HomeCompanyViewModel(@NonNull Application application) {
        super(application);
        couponsTitles = new ArrayList<>();
        couponsIds = new ArrayList<>();
        companyCouponRepository = new CompanyCouponRepository(application);
    }

    void init() {
        System.out.println("HomeCompanyViewModel: init");
        if (companyDetails.getId() != null && companyDetails.getToken() != null) {
            couponResponsesLiveData = companyCouponRepository.getCouponsByCompanyId(companyDetails.getId());
        }
    }

    public LiveData<List<CouponResponse>> getCouponResponsesLiveData() {
        System.out.println("HomeCompanyViewModel: getCouponResponsesLiveData");
        // sort the data before returning it
        couponResponsesLiveData = Transformations.map(couponResponsesLiveData, couponResponses -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                couponResponses.sort(Comparator.comparing(CouponResponse::getTitle));
            }
            return couponResponses;
        });
        return couponResponsesLiveData;
    }

    public void deleteCouponById(Long couponId) {
        companyCouponRepository.deleteCouponById(couponId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }
}

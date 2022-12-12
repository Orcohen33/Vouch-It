package com.example.myapplication.fragments.company.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.repository.CompanyCouponRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the ViewModel for the HomeCompanyFragment.
 * It contains the list of coupons that are displayed in the HomeCompanyFragment.
 */
public class HomeCompanyViewModel extends AndroidViewModel {

    private Long id;
    private String name;
    private String email;
    List<String> couponsTitles;

    private CompanyCouponRepository companyCouponRepository;
    private LiveData<List<CouponResponse>> couponResponsesLiveData;

    public HomeCompanyViewModel(@NonNull Application application) {
        super(application);
        couponsTitles = new ArrayList<>();
        System.out.println("HomeCompanyViewModel: " + id + ", " + name + ", " + email);
    }

    void init(){
        System.out.println("HomeCompanyViewModel: init");
        if (id!=null){
            companyCouponRepository = new CompanyCouponRepository();
            couponResponsesLiveData = companyCouponRepository.getCouponsByCompanyId(id);
        }
    }

    public LiveData<List<CouponResponse>> getCouponResponsesLiveData() {
        return couponResponsesLiveData;
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
}

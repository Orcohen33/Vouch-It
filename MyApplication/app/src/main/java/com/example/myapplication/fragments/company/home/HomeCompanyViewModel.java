package com.example.myapplication.fragments.company.home;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

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
    List<Long> couponsIds;

    private CompanyCouponRepository companyCouponRepository;
    private LiveData<List<CouponResponse>> couponResponsesLiveData;

    public HomeCompanyViewModel(@NonNull Application application) {
        super(application);
        couponsTitles = new ArrayList<>();
        couponsIds = new ArrayList<>();
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
        // sort the data before returning it
        couponResponsesLiveData = Transformations.map(couponResponsesLiveData, couponResponses -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                couponResponses.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
            }
            return couponResponses;
        });
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

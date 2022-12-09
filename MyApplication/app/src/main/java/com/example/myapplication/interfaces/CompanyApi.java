package com.example.myapplication.interfaces;

import com.example.myapplication.models.company.Company;
import com.example.myapplication.models.company.CompanySignIn;
import com.example.myapplication.models.company.CompanySignup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CompanyApi {
    @POST("/api/v1/company/login")
    Call<Company> login(@Body CompanySignIn companySignIn);

    @POST("/api/v1/company/signup")
    Call<Company> signup(@Body CompanySignup companySignup);
}

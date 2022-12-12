package com.vouchit.backend.service;

import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.request.company.CompanyRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CompanyResponse;
import com.vouchit.backend.model.response.CouponResponse;

import java.util.Set;

public interface CompanyService {



    CompanyResponse login(String email, String password);

    CompanyResponse signUp(String fullName, String email, String password);
    // Create
    CompanyResponse createCompany(CompanyRequest companyRequest);
    // Read
    Set<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long companyId);
    // Update
    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);
    // Delete
    String deleteCompany(Long companyId);


    // logic

    CouponResponse createCoupon(Long companyId, CouponRequest couponRequest);

    CouponResponse updateCoupon(Long companyId, CouponRequest couponRequest);

    CouponResponse deleteCoupon(Long companyId, CouponRequest couponRequest);

    Set<CouponResponse> getAllCouponsById(Long companyId);

    CouponResponse getCouponByIdAndCategory(Long companyId, Long categoryId, Long couponId);

    Set<CouponResponse> getCouponsByMaxPrice(Long companyId, Long categoryId);

    CompanyResponse mapCompanyToCompanyResponse(Company company);
    Company mapCompanyRequestToCompany(CompanyRequest companyRequest);
}

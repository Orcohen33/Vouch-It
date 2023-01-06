package com.vouchit.backend.service;

import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.request.company.CompanyRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CompanyCouponResponse;
import com.vouchit.backend.model.response.CompanyResponse;
import com.vouchit.backend.model.response.CouponResponse;

import java.util.Optional;
import java.util.Set;

public interface CompanyService {



    CompanyResponse login(String email, String password);

//    CompanyResponse signUp(String fullName, String email, String password);
    // Create
    CompanyResponse createCompany(CompanyRequest companyRequest);
    // Read
    Optional<CompanyResponse> getCompanyById(Long companyId);
    // Update
    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);
    // Delete
    String deleteCompany(Long companyId);


    CompanyResponse mapCompanyToCompanyResponse(Company company);
    Company mapCompanyRequestToCompany(CompanyRequest companyRequest);

    Company mapCompanyResponseToCompany(CompanyResponse companyResponse);
}

package com.vouchit.backend.service;

import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.request.user.company.CompanyRequest;
import com.vouchit.backend.model.response.CompanyResponse;

import java.util.Optional;

public interface CompanyService {

    // Read
    Optional<CompanyResponse> getCompanyById(Long companyId);
    // Update
    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);
    // Delete
    String deleteCompany(Long companyId);


    CompanyResponse mapCompanyToCompanyResponse(Company company);
    Company mapCompanyRequestToCompany(CompanyRequest companyRequest);

    Company mapCompanyResponseToCompany(CompanyResponse companyResponse);

    Company findById(Long companyId);
}

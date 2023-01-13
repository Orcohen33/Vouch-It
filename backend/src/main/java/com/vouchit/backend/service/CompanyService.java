package com.vouchit.backend.service;

import com.vouchit.backend.dto.request.user.company.CompanyRequest;
import com.vouchit.backend.dto.response.CompanyResponse;
import com.vouchit.backend.model.Company;
import com.vouchit.backend.model.User;

import java.util.Optional;

public interface CompanyService {

    Company createCompany(User user, String fullName);
    // Read
    Optional<CompanyResponse> getCompanyById(Long companyId);

    Company getCompanyByEmail(String email);
    // Update
    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);
    // Delete
    String deleteCompany(Long companyId);


    CompanyResponse mapCompanyToCompanyResponse(Company company);
    Company mapCompanyRequestToCompany(CompanyRequest companyRequest);

    Company mapCompanyResponseToCompany(CompanyResponse companyResponse);

    Company findById(Long companyId);
}

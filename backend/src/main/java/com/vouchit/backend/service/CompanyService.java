package com.vouchit.backend.service;

import com.vouchit.backend.model.request.CompanyRequest;
import com.vouchit.backend.model.response.CompanyResponse;

import java.util.Set;

public interface CompanyService {

    // Crud methods

    // Create
    CompanyResponse createCompany(CompanyRequest companyRequest);
    // Read
    Set<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long companyId);
    // Update
    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);
    // Delete
    void deleteCompany(Long companyId);
}

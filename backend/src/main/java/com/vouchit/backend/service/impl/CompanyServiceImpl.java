package com.vouchit.backend.service.impl;

import com.vouchit.backend.dto.request.user.company.CompanyRequest;
import com.vouchit.backend.dto.response.CompanyResponse;
import com.vouchit.backend.exception.company.CompanyNotFoundException;
import com.vouchit.backend.model.Company;
import com.vouchit.backend.model.User;
import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(User user, String fullName) {
        Company company = new Company();
        company.setUser(user);
        company.setName(fullName);
        return companyRepository.save(company);
    }

    @Override
    public Optional<CompanyResponse> getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .map(this::mapCompanyToCompanyResponse);
    }

    @Override
    public Company getCompanyByEmail(String email) {
        return companyRepository.findCompanyByEmail(email)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public String deleteCompany(Long companyId) {
        return null;
    }

    @Override
    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }

    @Override
    public Company mapCompanyRequestToCompany(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public Company mapCompanyResponseToCompany(CompanyResponse companyResponse) {
        return null;
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}

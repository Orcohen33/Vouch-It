package com.vouchit.backend.service.client;

import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.request.user.company.CompanyRequest;
import com.vouchit.backend.model.response.CompanyResponse;
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
    public Optional<CompanyResponse> getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .map(this::mapCompanyToCompanyResponse);
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

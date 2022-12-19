package com.vouchit.backend.service.impl;

import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.request.company.CompanyRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CompanyCouponResponse;
import com.vouchit.backend.model.response.CompanyResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyResponse login(String email, String password) {
        if (!companyRepository.existsCompanyByEmailAndPassword(email, password)) {
            return null;
        }
        Company company = companyRepository.findCompanyByEmailAndPassword(email, password);
        return modelMapper.map(company, CompanyResponse.class);
    }

//    @Override
//    public CompanyResponse signUp(String fullName, String email, String password) {
//        if (companyRepository.existsCompanyByEmail(email)) {
//            return null;
//        }
//        Company company = Company.builder()
//                .name(fullName)
//                .email(email)
//                .password(password)
//                .build();
//        companyRepository.save(company);
//        return modelMapper.map(company, CompanyResponse.class);
//    }

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        if (companyRepository.existsCompanyByEmail(companyRequest.getEmail())) {
            return null;
        }
        Company company = mapCompanyRequestToCompany(companyRequest);
        return mapCompanyToCompanyResponse(companyRepository.save(company));
    }

    @Override
    public Set<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream().map(this::mapCompanyToCompanyResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<CompanyResponse> getCompanyById(Long companyId) {
        var company = companyRepository.findById(companyId);
        return company.map(this::mapCompanyToCompanyResponse);
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public String deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
        return "Company deleted successfully";
    }

    // Logic
    @Override
    public CouponResponse createCoupon(Long companyId, CouponRequest couponRequest) {
        return null;
    }

    @Override
    public CouponResponse updateCoupon(Long companyId, CouponRequest couponRequest) {
        return null;
    }

    @Override
    public CouponResponse deleteCoupon(Long companyId, CouponRequest couponRequest) {
        return null;
    }

    @Override
    public Set<CompanyCouponResponse> getAllCouponsByCompanyId(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getCoupons)
                .map(coupons -> coupons.stream()
                        .map(coupon -> CompanyCouponResponse.builder()
                                .id(coupon.getId())
                                .title(coupon.getTitle())
                                .description(coupon.getDescription())
                                .price(coupon.getPrice())
                                .amount(coupon.getAmount())
                                .image(coupon.getImage())
                                .startDate(coupon.getStartDate())
                                .endDate(coupon.getEndDate())
                                .build())
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public CouponResponse getCouponByIdAndCategory(Long companyId, Long categoryId, Long couponId) {
        return null;
    }

    @Override
    public Set<CouponResponse> getCouponsByMaxPrice(Long companyId, Long categoryId) {
        return null;
    }
    // End of logic

    //  ================================= PRIVATE METHODS =================================
    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        var companyResponse = modelMapper.map(company, CompanyResponse.class);
        System.out.println("[mapCompanyToCompanyResponse]"+companyResponse);
        return companyResponse;
    }

    public Company mapCompanyRequestToCompany(CompanyRequest companyRequest) {
        return modelMapper.map(companyRequest, Company.class);
    }

    @Override
    public Company mapCompanyResponseToCompany(CompanyResponse companyResponse) {
        return modelMapper.map(companyResponse, Company.class);
    }
}

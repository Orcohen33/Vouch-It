package com.vouchit.backend.service.impl;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CompanyCouponResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.service.CategoryService;
import com.vouchit.backend.service.CompanyService;
import com.vouchit.backend.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final CompanyService companyService;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository,
                             CategoryService categoryService,
                             ModelMapper modelMapper,
                             CompanyService companyService) {
        this.couponRepository = couponRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
    }


    @Override
    public Set<CouponResponse> getAllCouponsByCategoryId(Long categoryId) {
        return couponRepository.findCouponsByCategoryId(categoryId)
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CompanyCouponResponse> getAllCouponsByCompanyId(Long companyId) {
        return couponRepository.getAllByCompanyId(companyId)
                .stream().map(this::mapCouponToCompanyCouponResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Coupon> getAllCouponsByIdIn(Set<Long> ids) {
        return couponRepository.getCouponsByIdIn(ids);
    }

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        var companyResponse = companyService.getCompanyById(couponRequest.getCompanyId()).orElseThrow(()
                -> new RuntimeException("Company " + couponRequest.getCompanyId() + " do not exist"));
        if (couponRepository.findCouponByTitle(couponRequest.getTitle()).isPresent())
            throw new RuntimeException("Coupon with title " + couponRequest.getTitle() + " already exist");
        System.out.println(couponRequest);
        var coupon = mapCouponRequestToCoupon(couponRequest);
        var savedCoupon = couponRepository.save(coupon);
        return mapCouponToCouponResponse(savedCoupon);
    }


    @Override
    public Set<CouponResponse> getAllCoupons() {
        return couponRepository.findAll()
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Coupon getCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    public CouponResponse updateCoupon(Long couponId, CouponRequest couponRequest) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        coupon.setTitle(couponRequest.getTitle());
        coupon.setDescription(couponRequest.getDescription());
        coupon.setCategory(
                        categoryService.getCategoryById(couponRequest.getCategoryId())
                        .map(categoryResponse -> modelMapper.map(categoryResponse, Category.class))
                        .orElseThrow(() -> new RuntimeException("Category not found")));
        coupon.setPrice(couponRequest.getPrice());
        coupon.setAmount(couponRequest.getAmount());
        coupon.setStartDate(LocalDate.parse(couponRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        coupon.setEndDate(LocalDate.parse(couponRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // TODO: Finish this method, add to categoryRepository the option the find category by name.
        return mapCouponToCouponResponse(couponRepository.save(coupon));
    }

    @Override
    public String deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
        return "Coupon deleted successfully";
    }



//    ================================= PRIVATE METHODS =================================
    public Coupon mapCouponRequestToCoupon(CouponRequest couponRequest) {
        return Coupon.builder()
                .title(couponRequest.getTitle())
                .description(couponRequest.getDescription())
                .price(couponRequest.getPrice())
                .amount(couponRequest.getAmount())
                .image(couponRequest.getImage())
                .startDate(LocalDate.parse(couponRequest.getStartDate()))
                .endDate(LocalDate.parse(couponRequest.getEndDate()))
                .company(companyService.mapCompanyResponseToCompany(companyService.getCompanyById(couponRequest.getCompanyId()).orElseThrow(()
                        -> new RuntimeException("Company " + couponRequest.getCompanyId() + " do not exist"))))
                .category(categoryService.mapCategoryResponseToCategory(categoryService.getCategoryById(couponRequest.getCategoryId()).orElseThrow(()
                        -> new RuntimeException("Category " + couponRequest.getCategoryId() + " do not exist"))))
                .build();
    }

    public CouponResponse mapCouponToCouponResponse(Coupon coupon) {
        return CouponResponse.builder()
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .price(coupon.getPrice())
                .amount(coupon.getAmount())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .category(categoryService.mapCategoryToCategoryResponse(coupon.getCategory()))
                .id(coupon.getId())
                .build();
//        return modelMapper.map(coupon, CouponResponse.class);
    }


    public CompanyCouponResponse mapCouponToCompanyCouponResponse(Coupon coupon) {
        return CompanyCouponResponse
                .builder()
                .id(coupon.getId())
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .price(coupon.getPrice())
                .amount(coupon.getAmount())
//                .image(coupon.getImage())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .build();
//        return modelMapper.map(coupon, CompanyCouponResponse.class);
    }
}
/*
    {
        "title":"testpostman",
        "description":"testPostManDescription",
        "startDate":"2022-12-12",
        "endDate":"2023-12-12",
        "amount":35,
        "price":255,
        "image":null,
        "companyId":5,
        "categoryId":3
    }
 */

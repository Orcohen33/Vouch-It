package com.vouchit.backend.service.impl;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.service.CategoryService;
import com.vouchit.backend.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository,
                             CategoryService categoryService, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Set<CouponResponse> getCouponsByCategory(Long categoryId) {
        Category category = categoryService
                .mapCategoryResponseToCategory(categoryService.getCategoryById(categoryId));

        return couponRepository.getCouponsByCategory(category)
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        Coupon coupon = mapCouponRequestToCoupon(couponRequest);
        return mapCouponToCouponResponse(couponRepository.save(coupon));
    }

    @Override
    public Set<CouponResponse> getAllCoupons() {
        return couponRepository.findAll()
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public CouponResponse getCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                .map(this::mapCouponToCouponResponse)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    public CouponResponse updateCoupon(Long couponId, CouponRequest couponRequest) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        coupon.setTitle(couponRequest.getTitle());
        coupon.setDescription(couponRequest.getDescription());

        // TODO: Finish this method, add to categoryRepository the option the find category by name.
        return null;
    }

    @Override
    public String deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
        return "Coupon deleted successfully";
    }

    @Override
    public Set<CouponResponse> getAllCouponsByCompanyId(Long companyId) {
        return couponRepository.getAllByCompanyId(companyId)
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
    }

//    ================================= PRIVATE METHODS =================================

    public Coupon mapCouponRequestToCoupon(CouponRequest couponRequest) {
        var modelMap = modelMapper.map(couponRequest, Coupon.class);
        return modelMap;
    }

    public CouponResponse mapCouponToCouponResponse(Coupon coupon) {
        var modelMap = modelMapper.map(coupon, CouponResponse.class);
        log.info("[CouponServiceImpl] modelMap: {}", modelMap);
        return modelMap;
    }
}

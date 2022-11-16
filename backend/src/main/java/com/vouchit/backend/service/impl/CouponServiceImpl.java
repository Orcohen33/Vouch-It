package com.vouchit.backend.service.impl;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Set<CouponResponse> getCouponsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        var coupons = couponRepository.getCouponsByCategory(category)
                .stream().map(this::mapCouponToCouponResponse)
                .collect(Collectors.toSet());
        log.info("coupons: {}", coupons);
        return coupons;
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

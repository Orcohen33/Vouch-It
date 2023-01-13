package com.vouchit.backend.service;

import com.vouchit.backend.dto.request.CouponRequest;
import com.vouchit.backend.dto.response.CompanyCouponResponse;
import com.vouchit.backend.dto.response.CouponResponse;
import com.vouchit.backend.model.Coupon;

import java.util.Set;

public interface CouponService {

    Set<CouponResponse> getAllCouponsByCategoryId(Long categoryId);      // TODO: Check if this is the right way to do it

    Set<CompanyCouponResponse> getAllCouponsByCompanyId(Long companyId);

    Set<Coupon> getAllCouponsByIdIn(Set<Long> ids);


    /**
     * Create a new coupon
     * @return CouponResponse
     */
    CouponResponse createCoupon(CouponRequest couponRequest);

    /**
     * Get all coupons
     * @return Set<CouponResponse>
     */
    Set<CouponResponse> getAllCoupons();

    /**
     * Get coupon by id
     * @param couponId
     * @return CouponResponse
     */
    Coupon getCouponById(Long couponId);


    /**
     * Update an existing coupon
     * @param couponId
     * @param couponRequest
     * @return CouponResponse
     */
    CouponResponse updateCoupon(Long couponId, CouponRequest couponRequest);

    /**
     * Delete an existing coupon
     * @param couponId
     * @return String
     */
    String deleteCoupon(Long couponId);


    CouponResponse mapCouponToCouponResponse(Coupon coupon);
}

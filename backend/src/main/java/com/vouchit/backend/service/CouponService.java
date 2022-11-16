package com.vouchit.backend.service;

import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CouponResponse;

import java.util.Set;

public interface CouponService {

    Set<CouponResponse> getCouponsByCategory(Long categoryId);      // TODO: Check if this is the right way to do it

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
    CouponResponse getCouponById(Long couponId);

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



}

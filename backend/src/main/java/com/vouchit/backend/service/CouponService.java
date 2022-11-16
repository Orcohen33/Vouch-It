package com.vouchit.backend.service;

import com.vouchit.backend.model.response.CouponResponse;

import java.util.Set;

public interface CouponService {

    Set<CouponResponse> getCouponsByCategory(Long categoryId);
}

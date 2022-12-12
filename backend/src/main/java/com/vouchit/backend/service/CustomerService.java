package com.vouchit.backend.service;


import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;

import java.util.Set;

public interface CustomerService {

    Customer login(String email, String password);

    Customer signUp(String fullName, String email, String password);

    boolean purchaseCoupon(Long customerId, Long couponId);

    Set<Coupon> getCustomerCoupons();

    Set<Coupon> getCustomerCouponsByCategory(CategoryRequest categoryRequest);

    Set<Coupon> getCustomerCouponsByMaxPrice(double maxPrice);

    Customer getCustomerDetails();

    CouponResponse mapCouponToCouponResponse(Coupon coupon);

    Coupon mapCouponRequestToCoupon(CouponRequest couponRequest);

    Category mapCategoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
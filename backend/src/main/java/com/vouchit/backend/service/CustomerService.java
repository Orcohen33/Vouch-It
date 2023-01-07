package com.vouchit.backend.service;


import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;

public interface CustomerService {

    Customer getCustomerById(Long id);

    CouponResponse mapCouponToCouponResponse(Coupon coupon);

    Coupon mapCouponRequestToCoupon(CouponRequest couponRequest);

    Category mapCategoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    void updateCustomer(Customer customer);
}

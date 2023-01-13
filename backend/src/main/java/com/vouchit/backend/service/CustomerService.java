package com.vouchit.backend.service;


import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.request.CouponRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.dto.response.CouponResponse;
import com.vouchit.backend.model.Category;
import com.vouchit.backend.model.Coupon;
import com.vouchit.backend.model.Customer;
import com.vouchit.backend.model.User;

public interface CustomerService {

    Customer createCustomer(User user, String fullName);

    Customer getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    CouponResponse mapCouponToCouponResponse(Coupon coupon);

    Coupon mapCouponRequestToCoupon(CouponRequest couponRequest);

    Category mapCategoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    void updateCustomer(Customer customer);
}

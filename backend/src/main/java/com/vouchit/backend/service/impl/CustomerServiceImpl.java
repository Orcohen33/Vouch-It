package com.vouchit.backend.service.impl;


import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.request.CouponRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.dto.response.CouponResponse;
import com.vouchit.backend.exception.customer.CustomerNotFoundException;
import com.vouchit.backend.model.Category;
import com.vouchit.backend.model.Coupon;
import com.vouchit.backend.model.Customer;
import com.vouchit.backend.model.User;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.repository.CustomerRepository;
import com.vouchit.backend.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    private final CouponRepository couponRepository;

    private final ModelMapper modelMapper;


    public CustomerServiceImpl(
                               CustomerRepository customerRepository,
                               CouponRepository couponRepository,
                               ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Customer createCustomer(User user, String fullName) {
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFullName(fullName);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public void updateCustomer(Customer customer) {
        // update customer details
        customerRepository.save(customer);
    }
    // =================================== MODEL MAPPER ===================================

    @Override
    public CouponResponse mapCouponToCouponResponse(Coupon coupon) {
        return modelMapper.map(coupon, CouponResponse.class);
    }

    @Override
    public Coupon mapCouponRequestToCoupon(CouponRequest couponRequest) {
        return modelMapper.map(couponRequest, Coupon.class);
    }

    @Override
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, Category.class);
    }

    @Override
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }
    // ======================================================================================

}



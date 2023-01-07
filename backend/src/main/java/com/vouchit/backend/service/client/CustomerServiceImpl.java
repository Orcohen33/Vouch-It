package com.vouchit.backend.service.client;


import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;
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
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
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



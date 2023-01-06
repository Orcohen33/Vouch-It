package com.vouchit.backend.service.client;


import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.model.response.CustomerSignInResponse;
import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.repository.CustomerRepository;
import com.vouchit.backend.security.LoginManager;
import com.vouchit.backend.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CompanyRepository companyRepository;

    private final CustomerRepository customerRepository;

    private final CouponRepository couponRepository;

    private final LoginManager loginManager;

    private final ModelMapper modelMapper;

    private Long customerId;

    public CustomerServiceImpl(CompanyRepository companyRepository,
                               CustomerRepository customerRepository,
                               CouponRepository couponRepository,
                               LoginManager loginManager,
                               ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
        this.loginManager = loginManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerSignInResponse login(String email, String password) {
        // TODO: לבדוק מה האם קיים משתמש כזה
//        if (!customerRepository.existsCustomerByEmailAndPassword(email, password)) {
//            return null;
//        }
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
//        CustomerSignInResponse customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        loginManager.setLoggedIn(true);
        return CustomerSignInResponse.builder()
                .id(customer.getId())
//                .email(customer.getEmail())
//                .password(customer.getPassword())
                .build();
    }

    @Override
    public Customer signUp(String fullName, String email, String password) {
        // TODO: לבדוק האם קיים לקוח כזה
//        if (customerRepository.existsCustomerByEmail(email)) {
//            return null;
//        }
        Customer customer = Customer.builder()
                .firstName(fullName)
//                .email(email)
//                .password(password)
                .build();
        customerRepository.save(customer);
        return customer;
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



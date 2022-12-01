package com.vouchit.backend.service.client;


import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;
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
    public Customer login(String email, String password) {
        if (customerRepository.existsCustomerByEmailAndPassword(email, password)) {
            Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
            loginManager.setLoggedIn(true);
            return customer;
        }
        return null;
    }

    @Override
    public Customer signUp(String fullName, String email, String password) {
        if (!customerRepository.existsCustomerByEmail(email)) {
            Customer customer = Customer.builder()
                    .firstName(fullName)
                    .email(email)
                    .password(password)
                    .build();
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    @Override
    public boolean purchaseCoupon(Long customerId, Long couponId) {
        // check if this customer already purchased this coupon
        var coupon = couponRepository.getReferenceById(couponId);
        if (couponRepository.existsCouponByCustomersIdAndId(customerId, coupon.getId())) {
            log.info("You already purchased this coupon");
            return false;
        }
        // cant purchase if amount is 0 or less
        else if (coupon.getAmount() <= 0) {
            log.info("This coupon is out of stock");
            return false;
        }
        // cant purchase if coupon expired
        else if (coupon.getEndDate().isBefore(coupon.getStartDate())) {
            log.info("This coupon is expired");
            return false;
        }
        // after purchase, amount of coupon should be decreased by 1
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.save(coupon);
        // add coupon to customer
        customerRepository.getReferenceById(customerId).getCoupons().add(coupon);
        customerRepository.save(customerRepository.getReferenceById(customerId));
        return true;
    }

    @Override
    public Set<Coupon> getCustomerCoupons() {
        // return all coupons of this customer
        return couponRepository.getAllByCustomersId(customerId);
    }

    @Override
    public Set<Coupon> getCustomerCouponsByCategory(CategoryRequest categoryRequest) {
        // return all coupons of this customer by category
        var coupons = couponRepository.getAllByCustomersId(customerId);
        var category = mapCategoryRequestToCategory(categoryRequest);
        return coupons
                .stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) {
        // return all coupons of this customer by max price
        var coupons = couponRepository.getAllByCustomersId(customerId);
        return coupons
                .stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(Collectors.toSet());
    }


    @Override
    public Customer getCustomerDetails() {
        // return customer details
        return customerRepository.getOne(customerId);
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



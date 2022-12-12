package com.vouchit.backend.controller;

import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.request.customer.CustomerSignInRequest;
import com.vouchit.backend.model.request.customer.CustomerSignupRequest;
import com.vouchit.backend.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin("*")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody CustomerSignInRequest customerSignInRequest) {
        log.info("CustomerController: login: logInRequest: {}", customerSignInRequest);

        Customer customer = customerService.login(customerSignInRequest.email(), customerSignInRequest.password());
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody CustomerSignupRequest customerSignupRequest) {
        log.info("CustomerController: signUp");
        log.info("CustomerController: signUp: logInRequest: {}", customerSignupRequest);
        Customer customer = customerService.signUp(customerSignupRequest.fullName(),
                customerSignupRequest.email(),
                customerSignupRequest.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/{customerId}/purchase/{couponId}")
    public ResponseEntity<?> purchase(@PathVariable("customerId") Long customerId, @PathVariable("couponId") Long couponId) {
        return ResponseEntity.ok(customerService.purchaseCoupon(customerId, couponId));
    }

    @GetMapping("/coupons")
    public ResponseEntity<?> getCustomerCoupons() {
        return ResponseEntity.ok(customerService.getCustomerCoupons());
    }

    @GetMapping("/coupons/category")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestBody CategoryRequest category) {
        return ResponseEntity.ok(customerService.getCustomerCouponsByCategory(category));
    }

    @GetMapping("/coupons/price/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@PathVariable double maxPrice) {
        return ResponseEntity.ok(customerService.getCustomerCouponsByMaxPrice(maxPrice));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCustomerDetails() {
        return ResponseEntity.ok(customerService.getCustomerDetails());
    }
}

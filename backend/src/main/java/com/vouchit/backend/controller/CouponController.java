package com.vouchit.backend.controller;

import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CompanyCouponResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.service.CouponService;
import com.vouchit.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class CouponController {
    private final CouponService couponService;
    private final CustomerService customerService;

    @Autowired
    public CouponController(CouponService couponService, CustomerService customerService) {
        this.couponService = couponService;
        this.customerService = customerService;
    }

    @GetMapping("/coupon/customer/{customerId}")
    public ResponseEntity<?> getAllCouponsOfCustomer(@PathVariable Long customerId) {
        var customer = customerService.getCustomerById(customerId);
        var coupons = customer.getCoupons();
        List<CouponResponse> couponResponses = coupons.stream()
                .map(couponService::mapCouponToCouponResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(couponResponses);
    }

    /*
    This endpoint will return all coupons for a given company
     */
    @GetMapping("/coupon/company/{companyId}")
    public ResponseEntity<Set<CompanyCouponResponse>> getAllCouponsByCompanyId(@PathVariable(name = "companyId") Long companyId) {
        System.out.println("CouponController: getAllCouponsByCompanyId: companyId: " + companyId);
        return ResponseEntity.ok(couponService.getAllCouponsByCompanyId(companyId));
    }

    @GetMapping("/coupon/category/{id}")
    public ResponseEntity<Set<CouponResponse>> getAllCouponsByCategoryId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.getAllCouponsByCategoryId(id));
    }

    @GetMapping("/coupon")
    public ResponseEntity<Set<CouponResponse>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable(name = "id") Long id) {
        Coupon coupon = couponService.getCouponById(id);
        return ResponseEntity.ok(couponService.mapCouponToCouponResponse(coupon));
    }

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        System.out.println("CouponController: createCoupon: couponRequest: " + couponRequest);
        return ResponseEntity.ok(couponService.createCoupon(couponRequest));
    }

    @PutMapping("/coupon/{id}")
    public ResponseEntity<CouponResponse> updateCoupon(@PathVariable(name = "id") Long id, @RequestBody CouponRequest couponRequest) {

        return ResponseEntity.ok(couponService.updateCoupon(id, couponRequest));
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.deleteCoupon(id));
    }


}

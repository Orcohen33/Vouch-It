package com.vouchit.backend.controller;

import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class CouponController {
// api/v1/company/2/coupon
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /*
    This endpoint will return all coupons for a given company
     */
    @GetMapping("/company/{companyId}/coupon")
    public ResponseEntity<Set<CouponResponse>> getAllCouponsByCompanyId(@PathVariable(name = "companyId") Long companyId) {
        System.out.println("CouponController: getAllCouponsByCompanyId: companyId: " + companyId);
        return ResponseEntity.ok(couponService.getAllCouponsByCompanyId(companyId));
    }

    @GetMapping("/coupon/category/{id}")
    public ResponseEntity<Set<CouponResponse>> getCouponsByCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.getCouponsByCategory(id));
    }

    @GetMapping("/coupon")
    public ResponseEntity<Set<CouponResponse>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
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

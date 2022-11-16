package com.vouchit.backend.controller;

import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<Set<CouponResponse>> getCouponsByCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.getCouponsByCategory(id));
    }

    @GetMapping
    public ResponseEntity<Set<CouponResponse>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        return ResponseEntity.ok(couponService.createCoupon(couponRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> updateCoupon(@PathVariable(name = "id") Long id, @RequestBody CouponRequest couponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, couponRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(couponService.deleteCoupon(id));
    }


}

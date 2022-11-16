package com.vouchit.backend.controller;

import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

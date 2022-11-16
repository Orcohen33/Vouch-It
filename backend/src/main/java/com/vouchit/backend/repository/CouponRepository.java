package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {


    Set<Coupon> getCouponsByCategory(Category category);
}

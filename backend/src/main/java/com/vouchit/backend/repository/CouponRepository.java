package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select c from Coupon c where c.company.id = ?1")
    Set<Coupon> getAllByCompanyId(Long companyId);

    @Query("select c from Coupon c where c.category.id = ?1")
    Set<Coupon> findCouponsByCategoryId(Long categoryId);

    Optional<Coupon> findCouponByTitle(String title);

    Set<Coupon> getCouponsByIdIn(Set<Long> ids);

}

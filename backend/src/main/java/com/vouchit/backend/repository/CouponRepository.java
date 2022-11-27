package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Set<Coupon> getCouponsByCategory(Category category);

    @Query("select (count(c) > 0) from Coupon c inner join c.customers customers where customers.id = ?1 and c.id = ?2")
    boolean existsCouponByCustomersIdAndId(Long customerId, Long id);

    @Query("select c from Coupon c inner join c.customers customers where customers.id = ?1")
    Set<Coupon> getAllByCustomersId(Long id);

}

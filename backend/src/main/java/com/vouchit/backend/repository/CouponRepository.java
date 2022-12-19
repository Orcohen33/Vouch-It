package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {


    @Query("select (count(c) > 0) from Coupon c inner join c.customers customers where customers.id = ?1 and c.id = ?2")
    boolean existsCouponByCustomersIdAndId(Long customerId, Long id);

    @Query("select c from Coupon c inner join c.customers customers where customers.id = ?1")
    Set<Coupon> getAllByCustomersId(Long id);

    @Query("select c from Coupon c where c.company.id = ?1")
    Set<Coupon> getAllByCompanyId(Long companyId);

    @Query("select c from Coupon c where c.category.id = ?1")
    Set<Coupon> findCouponsByCategoryId(Long categoryId);

    Optional<Coupon> findCouponByTitleAndCompanyId(String title, Long id);

}

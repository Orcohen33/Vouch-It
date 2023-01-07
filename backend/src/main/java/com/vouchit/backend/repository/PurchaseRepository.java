package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("select p from Purchase p inner join p.coupons coupons where coupons.id in ?1")
    List<Purchase> getAllByCoupons(Collection<Long> ids);
//    @Query("select p from Purchase p where p.coupons in ?1")
//    List<Purchase> getAllByCoupons(Collection<Coupon> coupons);

}

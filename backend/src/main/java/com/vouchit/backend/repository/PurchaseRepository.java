package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}

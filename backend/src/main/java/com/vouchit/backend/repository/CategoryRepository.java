package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.response.CouponResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}

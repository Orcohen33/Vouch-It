package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.response.CouponResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

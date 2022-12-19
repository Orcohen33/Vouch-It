package com.vouchit.backend.service;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CouponResponse;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Optional<CategoryResponse> getCategoryById(Long id);

    Set<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    String deleteCategory(CategoryRequest categoryRequest);

    CategoryResponse findCategoryByName(String name);

    CategoryResponse findCategoryById(Long id);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    Category mapCategoryRequestToCategory(CategoryRequest categoryRequest);

    Category mapCategoryResponseToCategory(CategoryResponse categoryResponse);

}

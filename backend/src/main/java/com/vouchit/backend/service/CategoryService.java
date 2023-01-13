package com.vouchit.backend.service;

import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.model.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Optional<CategoryResponse> getCategoryById(Long id);

    Set<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    String deleteCategory(CategoryRequest categoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    Category mapCategoryRequestToCategory(CategoryRequest categoryRequest);

    Category mapCategoryResponseToCategory(CategoryResponse categoryResponse);

}

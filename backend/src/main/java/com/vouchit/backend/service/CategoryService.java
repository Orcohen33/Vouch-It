package com.vouchit.backend.service;

import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.response.CategoryResponse;

import java.util.Set;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(Long id);

    Set<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    String deleteCategory(CategoryRequest categoryRequest);
}

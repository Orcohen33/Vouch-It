package com.vouchit.backend.service;

import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.response.CategoryResponse;

public interface CategoryService {

    void createCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(Long id);

    void updateCategory(CategoryRequest categoryRequest);

    String deleteCategory(CategoryRequest categoryRequest);
}

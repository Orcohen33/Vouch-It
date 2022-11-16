package com.vouchit.backend.service.impl;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {

    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapCategoryToCategoryResponse)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest) {

    }

    @Override
    public String deleteCategory(CategoryRequest categoryRequest) {
        return "";
    }
//    ================================= PRIVATE METHODS =================================
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, Category.class);
    }
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }
}

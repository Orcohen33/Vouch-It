package com.vouchit.backend.service.impl;

import com.vouchit.backend.exception.category.CategoryNotFoundException;
import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.request.CategoryRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapCategoryToCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Set<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapCategoryToCategoryResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findByName(categoryRequest.getName());
        if (category.isPresent()) {
            Category categoryToUpdate = category.get();
            categoryToUpdate.setName(categoryRequest.getName());
            categoryRepository.save(categoryToUpdate);
            return modelMapper.map(categoryToUpdate, CategoryResponse.class);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    public String deleteCategory(CategoryRequest categoryRequest) {
        return categoryRepository.findByName(categoryRequest.getName())
                .map(category -> {
                    categoryRepository.delete(category);
                    return "Category deleted successfully";
                })
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
//    ================================= PRIVATE METHODS =================================
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, Category.class);
    }
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }
}

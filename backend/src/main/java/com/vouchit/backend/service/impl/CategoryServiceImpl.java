package com.vouchit.backend.service.impl;

import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.model.Category;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<CategoryResponse> getCategoryById(Long id) {
        var category = categoryRepository.findById(id);
        return category.map(this::mapCategoryToCategoryResponse);
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

    @Override
    public Category mapCategoryResponseToCategory(CategoryResponse categoryResponse) {
        return modelMapper.map(categoryResponse, Category.class);
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }
}

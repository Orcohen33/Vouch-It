package com.vouchit.backend.controller;

import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found")));
    }


    @GetMapping
    public ResponseEntity<Set<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequest));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryRequest));
    }



}

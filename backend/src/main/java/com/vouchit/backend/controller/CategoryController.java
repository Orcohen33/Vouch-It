package com.vouchit.backend.controller;

import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.service.CategoryService;
import com.vouchit.backend.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
@Transactional
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        log.info("Get category by id: {}", id);
        log.info("Category: {}", categoryService.getCategoryById(id));
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }



}

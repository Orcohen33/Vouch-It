package com.vouchit.backend.controller;

import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryResponse categoryResponse;

    @Test
    void canCreateNewCategory() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        request.setRequestURI("/api/v1/category");

        when(categoryService.createCategory(any(CategoryRequest.class))).thenReturn(categoryResponse);

        CategoryRequest categoryRequest = new CategoryRequest("test");
        ResponseEntity<?> responseEntity = categoryController.createCategory(categoryRequest);

        assert responseEntity.getStatusCodeValue() == 200;
        assert Objects.requireNonNull(responseEntity.getBody()).equals(categoryResponse);
        assert Objects.equals(request.getRequestURI(), "/api/v1/category");


    }

    @Test
    void canGetCategoryById() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        request.setRequestURI("/api/v1/category/1");

        when(categoryService.getCategoryById(any(Long.class))).thenReturn(Optional.of(categoryResponse));

        ResponseEntity<?> responseEntity = categoryController.getCategoryById(1L);

        assert responseEntity.getStatusCodeValue() == 200;
        assert Objects.requireNonNull(responseEntity.getBody()).equals(categoryResponse);
    }

    @Test
    void canGetAllCategories() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(categoryService.getAllCategories()).thenReturn(null);

        ResponseEntity<?> responseEntity = categoryController.getAllCategories();

        assert responseEntity.getStatusCodeValue() == 200;
    }
}
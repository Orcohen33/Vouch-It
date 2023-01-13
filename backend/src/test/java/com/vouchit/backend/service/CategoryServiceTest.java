package com.vouchit.backend.service;

import com.vouchit.backend.dto.request.CategoryRequest;
import com.vouchit.backend.dto.response.CategoryResponse;
import com.vouchit.backend.model.Category;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testCreateCategory() {
        // Set up mock behavior
        when(modelMapper.map(any(CategoryRequest.class), eq(Category.class))).thenReturn(new Category());
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(modelMapper.map(any(Category.class), eq(CategoryResponse.class))).thenReturn(new CategoryResponse());

        // Call the method under test
        CategoryRequest categoryRequest = new CategoryRequest();
        CategoryResponse response = categoryService.createCategory(categoryRequest);

        // Verify that the mock objects were used correctly
        verify(modelMapper).map(categoryRequest, Category.class);
        verify(categoryRepository).save(any(Category.class));
        verify(modelMapper).map(any(Category.class), eq(CategoryResponse.class));

        // Add any additional assertions as needed
        assertEquals(response.getName(), categoryRequest.getName());
    }



    @Test
    void getCategoryById() {
        // set up mock behavior
        when(modelMapper.map(any(Category.class), eq(CategoryResponse.class)))
                .thenReturn(CategoryResponse
                        .builder()
                        .name("test")
                        .build());
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(new Category()));


        // Call the method under test
        final Optional<CategoryResponse> category;
        category = categoryService.getCategoryById(1999L);

        // Verify that the mock objects were used correctly
        verify(categoryRepository).findById(anyLong());
        verify(modelMapper).map(any(Category.class), eq(CategoryResponse.class));

        // Add any additional assertions as needed
        assertEquals(category.get().getName(), "test");

    }

    @Test
    void getAllCategories() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }
}
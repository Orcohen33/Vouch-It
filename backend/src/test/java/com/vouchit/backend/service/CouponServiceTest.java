package com.vouchit.backend.service;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.request.CouponRequest;
import com.vouchit.backend.model.response.CategoryResponse;
import com.vouchit.backend.model.response.CompanyResponse;
import com.vouchit.backend.model.response.CouponResponse;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.service.impl.CategoryServiceImpl;
import com.vouchit.backend.service.impl.CouponServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {


    @Mock
    private CouponRepository couponRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    private CompanyService companyService;
    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    void getAllCouponsByCategoryId() {
    }

    @Test
    void getAllCouponsByCompanyId() {
    }

    @Test
    void getAllCouponsByIdIn() {
    }

    @Test
    void createCoupon() {
//        // Set up mock behavior
//        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(new Category()));
//        when(categoryService.mapCategoryResponseToCategory(any(CategoryResponse.class))).thenReturn(new Category());
//        when(categoryService.getCategoryById(anyLong())).thenReturn(Optional.of(new CategoryResponse()));
//        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
//        when(companyService.getCompanyById(anyLong())).thenReturn(Optional.of(new CompanyResponse()));
//        when(couponRepository.findCouponByTitle(String.valueOf(any(CouponRequest.class)))).thenReturn(Optional.of(new Coupon()));
//        when(couponService.mapCouponRequestToCoupon(CouponRequest.builder()
//                .title("title")
//                .description("description")
//                .categoryId(1L)
//                .companyId(1L)
//                .amount(1)
//                .price(1.0)
//                .startDate("2023-01-01")
//                .endDate("2023-01-01")
//                .build())).thenReturn(Coupon.builder()
//                        .title("title")
//                        .description("description")
//                        .category(new Category())
//                        .company(new Company())
//                        .amount(1)
//                        .price(1.0)
//                        .startDate(LocalDate.of(2023, 1, 1))
//                        .endDate(LocalDate.of(2023, 1, 1))
//                        .build());
//        when(couponRepository.save(any(Coupon.class))).thenReturn(new Coupon());
//        when(couponService.mapCouponToCouponResponse(any(Coupon.class))).thenReturn(new CouponResponse());
//        when(categoryService.getCategoryById(anyLong())).thenReturn(Optional.of(new CategoryResponse()));
//
//        when(companyService.mapCompanyResponseToCompany(any(CompanyResponse.class))).thenReturn(new Company());
//        when(companyService.getCompanyById(anyLong())).thenReturn(Optional.of(new CompanyResponse()));
////        when(categoryService.mapCategoryResponseToCategory(any(CategoryResponse.class))).thenReturn(new Category());
//        when(modelMapper.map(any(Coupon.class), any(Class.class))).thenReturn(new CouponResponse());
//        // Call the method under test
//        CouponRequest couponRequest = CouponRequest.builder()
//                .title("title")
//                .description("description")
//                .categoryId(1L)
//                .companyId(1L)
//                .amount(1)
//                .price(1.0)
//                .startDate("2023-01-01")
//                .endDate("2023-01-01")
//                .build();
//        CouponResponse response = couponService.createCoupon(couponRequest);
//
//        // Verify that the mock objects were used correctly
////        verify(modelMapper).map(couponRequest, Coupon.class);
//        verify(couponRepository).save(any(Coupon.class));
////        verify(modelMapper).map(any(Coupon.class), eq(CouponResponse.class));
//
//        // Add any additional assertions as needed
//        assertEquals(response.getTitle(), couponRequest.getTitle());
//        assertEquals(response.getPrice(), couponRequest.getPrice());
//        assertEquals(response.getAmount(), couponRequest.getAmount());


            // Set up mock behavior
            when(companyService.getCompanyById(1L)).thenReturn(Optional.of(new CompanyResponse()));
            when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(new CategoryResponse()));
            when(couponRepository.findCouponByTitle("Test Coupon")).thenReturn(Optional.empty());
            when(couponService.mapCouponRequestToCoupon(CouponRequest.builder()
                            .title("Test Coupon")
                            .description("Test Description")
                            .categoryId(1L)
                            .companyId(1L)
                            .amount(1)
                            .price(1.0)
                            .startDate("2023-01-01")
                            .endDate("2023-01-01")
                    .build())).thenReturn(Coupon.builder()
                            .title("Test Coupon")
                            .description("Test Description")
                            .category(new Category())
                            .company(new Company())
                            .amount(1)
                            .price(1.0)
                            .startDate(LocalDate.of(2023, 1, 1))
                            .endDate(LocalDate.of(2023, 1, 1)).build());
            when(couponService.mapCouponToCouponResponse(Coupon.builder()
                    .title("Test Coupon")
                    .description("Test Description")
                    .category(new Category())
                    .company(new Company())
                    .amount(1)
                    .price(1.0)
                    .startDate(LocalDate.parse("2023-01-01"))
                    .endDate(LocalDate.parse("2023-01-01"))
                    .build())).thenReturn(any());
            // Create a coupon request
            CouponRequest couponRequest = CouponRequest.builder()
                    .title("Test Coupon")
                    .description("Test Description")
                    .categoryId(1L)
                    .companyId(1L)
                    .amount(1)
                    .price(1.0)
                    .startDate("2023-01-01")
                    .endDate("2023-01-01")
                    .build();

            // Call the createCoupon method
            CouponResponse response = couponService.createCoupon(couponRequest);

            // Assert that the method returned the expected result
            assertEquals("Test Coupon", response.getTitle());
            assertEquals(1.0, response.getPrice(), 0.1);

    }

    @Test
    void getAllCoupons() {
    }

    @Test
    void getCouponById() {
    }

    @Test
    void updateCoupon() {
    }

    @Test
    void deleteCoupon() {
    }

    @Test
    void mapCouponToCouponResponse() {
    }
}
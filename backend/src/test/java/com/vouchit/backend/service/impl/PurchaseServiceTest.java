//package com.vouchit.backend.service.impl;
//
//import com.vouchit.backend.model.entity.Coupon;
//import com.vouchit.backend.model.entity.Customer;
//import com.vouchit.backend.model.entity.Purchase;
//import com.vouchit.backend.model.request.PurchaseRequest;
//import com.vouchit.backend.repository.PurchaseRepository;
//import com.vouchit.backend.service.CouponService;
//import com.vouchit.backend.service.CustomerService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PurchaseServiceTest {
//
//    @Mock
//    private PurchaseRepository purchaseRepository;
//    @InjectMocks
//    private PurchaseServiceImpl purchaseService;
//
//    @Mock
//    private PurchaseRequest request;
//    @Mock
//    private Purchase purchase;
//
//    @Test
//    void purchaseCoupons() {
//
//
//    }
//
//    @Test
//    public void testPurchaseCoupons() {
//        // Create mock objects
//        CustomerService customerService = mock(CustomerService.class);
//        CouponService couponService = mock(CouponService.class);
//        PurchaseRepository purchaseRepository = mock(PurchaseRepository.class);
//        PurchaseServiceImpl purchaseService = mock(PurchaseServiceImpl.class);
//
//        // Set up mock object behavior
//        when(customerService.getCustomerDetails(anyLong())).thenReturn(new Customer());
//        when(couponService.findCouponsByIdIn(new HashSet<>(Arrays.asList(1L, 2L, 3L)))).thenReturn(new HashSet<Coupon>());
//
//        // Create a PurchaseRequest object
//        PurchaseRequest request = new PurchaseRequest();
//        request.setCustomerId(1L);
//        request.setCouponIds(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
//        request.setTotalPrice(30.0);
//
//        // Call the purchaseCoupons function
//        purchaseService.purchaseCoupons(request);
//
//        // Verify mock object behavior
//        verify(customerService).getCustomerDetails(1L);
//        verify(couponService).findCouponsByIdIn(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
//        verify(purchaseRepository).save(any(Purchase.class));
//    }
//
//    @Test
//    public void testPurchaseCoupons2() {
//        // Create mock objects
//        CustomerService customerService = mock(CustomerService.class);
//        CouponService couponService = mock(CouponService.class);
//        PurchaseRepository purchaseRepository = mock(PurchaseRepository.class);
//
//        // Set up mock behavior
//        when(customerService.getCustomerDetails(anyLong())).thenReturn(new Customer());
//        when(couponService.findCouponsByIdIn(anySet())).thenReturn(new HashSet<>());
//
//        // Create the PurchaseServiceImpl object
//        PurchaseServiceImpl purchaseService = new PurchaseServiceImpl(couponService, customerService, purchaseRepository);
//
//        // Create the request object
//        PurchaseRequest request = new PurchaseRequest();
//        request.setCustomerId(1L);
//        request.setCouponIds(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
//        request.setTotalPrice(100.0);
//
//        // Call the purchaseCoupons function
//        purchaseService.purchaseCoupons(request);
//        purchaseRepository.save(any(Purchase.class));
//
//        // Make assertions on the expected behavior
//        verify(customerService).getCustomerDetails(anyLong());
//        verify(couponService).findCouponsByIdIn(anySet());
//        verify(purchaseRepository).save(any(Purchase.class));
//    }
//
//    // Create the PurchaseServiceImpl object
////        PurchaseServiceImpl purchaseService = new PurchaseServiceImpl( couponService,customerService,  purchaseRepository);
//
//    // Call the purchaseCoupons function
////        purchaseService.purchaseCoupons(request);
//    @Test
//    public void testPurchaseCoupons3() {
//        // Create mock objects
//        CustomerService customerService = mock(CustomerService.class);
//        CouponService couponService = mock(CouponService.class);
//        PurchaseRepository purchaseRepository = mock(PurchaseRepository.class);
//        PurchaseServiceImpl purchaseService = mock(PurchaseServiceImpl.class);
//
//        // Set up mock behavior
//        when(customerService.getCustomerDetails(anyLong()))
//                .thenReturn(Customer
//                        .builder()
//                        .id(1L)
//                        .firstName("John")
//                        .lastName("Doe")
//                        .email("john@john.com")
//                        .password("123456")
//                        .coupons(new HashSet<>())
//                        .build());
//        when(couponService.findCouponsByIdIn(anySet())).thenReturn(new HashSet<>(Arrays.asList(
//                Coupon
//                        .builder()
//                        .id(1L)
//                        .title("Coupon 1")
//                        .description("Description 1")
//                        .price(10.0)
//                        .amount(10)
//                        .build(),
//                Coupon
//                        .builder()
//                        .id(2L)
//                        .title("Coupon 2")
//                        .description("Description 2")
//                        .amount(10)
//                        .price(20.0)
//                        .build()
//        )));
//
//        // Create the request object
//        PurchaseRequest request = new PurchaseRequest();
//        request.setCustomerId(1L);
//        request.setCouponIds(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
//        request.setTotalPrice(100.0);
//
//        // Initialize the Purchase object with required data
//        Purchase purchase = Purchase.builder()
//                .customer(customerService.getCustomerDetails(request.getCustomerId()))
//                .coupons(couponService.findCouponsByIdIn(request.getCouponIds()))
//                .totalPrice(request.getTotalPrice())
//                .date(LocalDateTime.now())
//                .build();
//
//        // Make sure the Purchase object is passed to the purchaseRepository.save() method
//        purchaseRepository.save(purchase);
//
//        // Make assertions on the expected behavior
//        verify(purchaseService).purchaseCoupons(request);
//        verify(customerService).getCustomerDetails(anyLong());
//        verify(couponService).findCouponsByIdIn(anySet());
//        verify(purchaseRepository).save(any(Purchase.class));
//    }
//}
//
//

package com.vouchit.backend.service.impl;

import com.vouchit.backend.dto.response.CouponResponse;
import com.vouchit.backend.model.Coupon;
import com.vouchit.backend.model.Purchase;
import com.vouchit.backend.repository.PurchaseRepository;
import com.vouchit.backend.service.CouponService;
import com.vouchit.backend.service.CustomerService;
import com.vouchit.backend.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final CouponService couponService;
    private final CustomerService customerService;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(CouponService couponService,
                               CustomerService customerService,
                               PurchaseRepository purchaseRepository) {
        this.couponService = couponService;
        this.customerService = customerService;
        this.purchaseRepository = purchaseRepository;
    }

//    @Override
//    public void purchaseCoupons(PurchaseRequest request){
//        Purchase purchase = new Purchase();
//        // Validate the request
//        // 1. Check if the customer exists
//        var customer = customerService.getCustomerDetails(request.getCustomer());
//        if (customer == null) {return;}
//
//        // 2. Check if the coupons exist
//        Set<Coupon> coupons = couponService.findCouponsByIdIn(request.getCouponIds());
//        if (coupons.size() != request.getCouponIds().size()) {return;}
//
//        // 3. Check if the coupons are not expired
//        coupons
//                .stream()
//                .filter(coupon -> coupon.getEndDate().isBefore(LocalDate.now()))
//                .findAny()
//                .ifPresent(coupon -> {throw new PurchaseException("Coupon is expired");});
//
//        // Check availability
//        // 1. Check if the coupons are not already purchased
//        customer.getCoupons().forEach(coupon -> {
//            if (coupons.contains(coupon)) {
//                throw new PurchaseException("Coupon is already purchased");
//            }
//        });
//        // 2. Check if the amount of coupons is not more than the available amount
//        coupons.forEach(coupon -> {
//            if (coupon.getAmount() < 1) {
//                throw new RuntimeException("Coupon is out of stock");
//            }
//        });
//
//        // Update the amount of coupons
//        coupons.forEach(coupon -> {
//            coupon.setAmount(coupon.getAmount() - 1);
//        });
//
//        // Update the customer
//        customer.getCoupons().addAll(coupons);
//        customerService.updateCustomer(customer);
//
//        // Update the purchase
//        purchase.setCustomer(customer);
//        purchase.setCoupons(coupons);
//        purchase.setDate(LocalDate.now().atStartOfDay());
//        purchase.setTotalPrice(request.getTotalPrice());
//        purchaseRepository.save(purchase);
//    }

    @Override
    public boolean purchaseCoupon(Long customerId, Set<Long> couponIds) {
        // TODO: Complete the implementation
        var customer = customerService.getCustomerById(customerId);
        var coupons = couponService.getAllCouponsByIdIn(couponIds);
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setDate(LocalDate.now().atStartOfDay());
        double totalPrice = 0;
        for (Coupon coupon : coupons) {
            if (coupon.getAmount() < 1) {
                return false;
            }
            coupon.setAmount(coupon.getAmount() - 1);
            customer.getCoupons().add(coupon);
            totalPrice += coupon.getPrice();
        }
        purchase.setCoupons(coupons);
        purchase.setTotalPrice(totalPrice);
        purchaseRepository.save(purchase);
        customerService.updateCustomer(customer);
        return true;
    }



//    @Override
//    public List<Purchase> getAllPurchasesOfCompany(Long companyId) {
//        return purchaseRepository.getPurchasesByCompanyId(companyId);
//    }


    private Coupon mapCouponResponseToCoupon(CouponResponse coupon) {
        return Coupon.builder()
                .id(coupon.getId())
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .amount(coupon.getAmount())
                .price(coupon.getPrice())
                .image(null)
                .build();
    }


}

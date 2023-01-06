package com.vouchit.backend.controller;

import com.vouchit.backend.model.request.PurchaseRequest;
import com.vouchit.backend.service.PurchaseService;
import com.vouchit.backend.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    private final PurchaseService purchaseServiceImpl;

    @Autowired
    public PurchaseController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping("/purchase")
    @Transactional
    public void purchaseCoupon(@RequestBody PurchaseRequest purchaseRequest) {
        this.purchaseServiceImpl.purchaseCoupon(purchaseRequest.getCustomerId(), purchaseRequest.getCouponIds());
    }

// get all purchases of a company
//    @GetMapping("company/{companyId}/purchases")
//    public ResponseEntity<?> getAllPurchasesOfCompany(@PathVariable Long companyId) {
//        return ResponseEntity.ok(this.purchaseServiceImpl.getAllPurchasesOfCompany(companyId));
//    }

}

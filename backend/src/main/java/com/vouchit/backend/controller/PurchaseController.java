package com.vouchit.backend.controller;

import com.vouchit.backend.model.request.PurchaseRequest;
import com.vouchit.backend.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    private final PurchaseServiceImpl purchaseServiceImpl;

    @Autowired
    public PurchaseController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping("/purchase")
    @Transactional
    public void purchaseCoupon(@RequestBody PurchaseRequest purchaseRequest) {
        this.purchaseServiceImpl.purchaseCoupon(purchaseRequest.getCustomerId(), purchaseRequest.getCouponIds());
    }
}

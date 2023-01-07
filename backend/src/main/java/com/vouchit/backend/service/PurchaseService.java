package com.vouchit.backend.service;

import java.util.Set;

public interface PurchaseService {

    boolean purchaseCoupon(Long customerId, Set<Long> couponId);

}

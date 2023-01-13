package com.vouchit.backend.utils;


import com.vouchit.backend.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * this class run single thread and check every 24 hours if any coupon is expired
 * if coupon is expired it will be deleted from the database
 */
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
@Slf4j
public class CouponExpirationDailyJob implements Runnable {

    private final CouponRepository couponRepository;


    public CouponExpirationDailyJob(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
        this.quit = false;
    }

    private boolean quit;

    public void stop() {
        this.quit = true;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            while (!quit) {
                try {
                    var coupons = couponRepository.findAll();
                    coupons.forEach(coupon -> {
                        if (coupon.getEndDate().isBefore(LocalDate.now())) {
                            log.info("Coupon with id: " + coupon.getId() + " is expired");
                            couponRepository.delete(coupon);
                        }
                    });
                    Thread.sleep(1000 * 60 * 60 * 24);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void run() {
        this.start();
    }
}

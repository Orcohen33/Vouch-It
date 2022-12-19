package com.vouchit.backend.model.response;

import com.vouchit.backend.model.entity.Coupon;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Coupon} entity
 */
@Data
@Builder
public class CompanyCouponResponse implements Serializable {
    private final Long id;
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer amount;
    private final Double price;
    private final Byte[] image;
}
package com.vouchit.backend.model.response;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CouponResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer amount;
    private Double price;
    private String image;
    private CompanyResponse company;
    private CategoryResponse category;
}

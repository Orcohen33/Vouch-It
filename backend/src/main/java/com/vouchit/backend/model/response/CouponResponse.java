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
    private Integer amount;
    private Double price;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
//    private CompanyResponse company;
    private CategoryResponse category;
}

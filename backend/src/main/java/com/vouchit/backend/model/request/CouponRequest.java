package com.vouchit.backend.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CouponRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;

    @NotNull(message = "start date is required")
    private String startDate;

    @NotNull(message = "end date is required")
    private String endDate;
    @Min(1)
    private Integer amount;
    @Min(1)
    private Double price;
    private Byte[] image;
    @NotNull(message = "Company is required")
    private Long companyId;
    @NotNull(message = "category is required")
    private Long categoryId;

}

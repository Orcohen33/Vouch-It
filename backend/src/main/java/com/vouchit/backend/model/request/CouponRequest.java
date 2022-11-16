package com.vouchit.backend.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"company"})
public class CouponRequest {

    @NotNull(message = "Company is required")
    private CompanyRequest company;
    @NotNull(message = "category is required")
    private CategoryRequest category;
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;
    @NotNull(message = "start date is required")
    private LocalDate startDate;
    @NotNull(message = "end date is required")
    private LocalDate endDate;
    @Min(1)
    private Integer amount;
    @Min(1)
    private Double price;
    private String image;


}

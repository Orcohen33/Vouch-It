package com.vouchit.backend.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;
}

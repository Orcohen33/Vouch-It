package com.vouchit.backend.model.request.company;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CouponCompanyRequest {

    @NotBlank(message = "name is required")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
}

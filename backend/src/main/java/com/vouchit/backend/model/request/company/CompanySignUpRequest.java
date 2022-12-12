package com.vouchit.backend.model.request.company;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record CompanySignUpRequest(@NotBlank String fullName,
                                   @Email String email,
                                   @Min(6) String password) {
}

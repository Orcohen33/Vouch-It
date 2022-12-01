package com.vouchit.backend.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record CustomerSignupRequest(@NotBlank String fullName,
                                    @Email String email,
                                    @Min(6) String password) {
}

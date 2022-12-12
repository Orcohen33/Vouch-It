package com.vouchit.backend.model.request.customer;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank(message = "first name is required")
    @Size(min = 2, max = 50, message = "first name must be between 2 and 50 characters")
    private String firstName;
    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 50, message = "last name must be between 2 and 50 characters")
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    private String password;
}

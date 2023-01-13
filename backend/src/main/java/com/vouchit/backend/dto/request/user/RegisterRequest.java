package com.vouchit.backend.dto.request.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Boolean isCompany;
}

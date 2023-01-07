package com.vouchit.backend.model.request.user;

import lombok.Builder;

@Builder
public record UserSignInRequest(String email, String password, boolean isCompany) {

}

package com.vouchit.backend.model.request;

public record CustomerSignupRequest(String fullName, String email, String password) {
}

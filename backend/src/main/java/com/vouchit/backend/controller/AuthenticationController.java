package com.vouchit.backend.controller;

import com.vouchit.backend.dto.request.user.RegisterRequest;
import com.vouchit.backend.dto.request.user.UserSignInRequest;
import com.vouchit.backend.dto.response.AuthenticationResponse;
import com.vouchit.backend.service.impl.client.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody UserSignInRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}

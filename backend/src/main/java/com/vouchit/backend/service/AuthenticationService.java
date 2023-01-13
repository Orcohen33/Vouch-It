package com.vouchit.backend.service;

import com.vouchit.backend.dto.request.user.RegisterRequest;
import com.vouchit.backend.dto.request.user.UserSignInRequest;
import com.vouchit.backend.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request) ;

    public AuthenticationResponse authenticate(UserSignInRequest request) ;
}

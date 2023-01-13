package com.vouchit.backend.service.impl.client;

import com.vouchit.backend.config.service.JwtService;
import com.vouchit.backend.dto.request.user.RegisterRequest;
import com.vouchit.backend.dto.request.user.UserSignInRequest;
import com.vouchit.backend.dto.response.AuthenticationResponse;
import com.vouchit.backend.model.Company;
import com.vouchit.backend.model.Customer;
import com.vouchit.backend.model.User;
import com.vouchit.backend.repository.AuthorityRepository;
import com.vouchit.backend.service.AuthenticationService;
import com.vouchit.backend.service.CompanyService;
import com.vouchit.backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final CustomerService customerService;

    private final CompanyService companyService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // todo check validation of request (if user already exists, etc)
        // if so then check if user is customer or company

        System.out.println(request);
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        if (!request.getIsCompany()) {
            user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_CUSTOMER")));
            var c = customerService.createCustomer(user, request.getFullName());
            var jwtToken = jwtService.generateAccessToken(user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .email(user.getUsername())
                    .id(c.getId())
                    .build();
        } else {
            user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_COMPANY")));
            var c = companyService.createCompany(user, request.getFullName());

            var jwtToken = jwtService.generateAccessToken(user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .email(user.getUsername())
                    .id(c.getId())
                    .build();
        }
    }
    @Override
    public AuthenticationResponse authenticate(UserSignInRequest request) {
        String fullName;
        Long id;
        String refreshToken;

        // authenticate the user
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        if (!auth.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        }
        // retrieve customer or company (depending on the user request)
        var co = request.isCompany() ? companyService.getCompanyByEmail(request.email()) : customerService.getCustomerByEmail(request.email());
        if (co == null) {
            throw new RuntimeException("User not found");
        }
        if (co instanceof Customer customer) {
            fullName = customer.getFullName();
            id = customer.getId();
        } else {
            Company company = (Company) co;
            fullName = company.getName();
            id = company.getId();
        }
        var jwtAccessToken = jwtService.generateAccessToken((UserDetails) auth.getPrincipal());
        var jwtRefreshToken = jwtService.generateRefreshToken((UserDetails) auth.getPrincipal());


        return AuthenticationResponse.builder()
                .email(request.email())
                .id(id)
                .fullName(fullName)
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

}

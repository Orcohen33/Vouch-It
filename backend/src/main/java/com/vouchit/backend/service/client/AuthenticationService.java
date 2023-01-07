package com.vouchit.backend.service.client;

import com.vouchit.backend.config.service.JwtService;
import com.vouchit.backend.model.entity.Company;
import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.entity.User;
import com.vouchit.backend.model.request.user.RegisterRequest;
import com.vouchit.backend.model.request.user.UserSignInRequest;
import com.vouchit.backend.model.response.AuthenticationResponse;
import com.vouchit.backend.repository.AuthorityRepository;
import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.repository.CustomerRepository;
import com.vouchit.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

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
            var c = customerRepository.save(Customer.builder()
                    .user(user)
                    .fullName(request.getFullName())
                    .build());
            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .email(user.getUsername())
                    .id(c.getId())
                    .build();
        } else {
            user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_COMPANY")));
            var c = companyRepository.save(Company.builder()
                    .user(user)
                    .name(request.getFullName())
                    .build());

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .email(user.getUsername())
                    .id(c.getId())
                    .build();
        }
    }

    public AuthenticationResponse authenticate(UserSignInRequest request) {
        String fullName;
        Long id;
        String refreshToken;

        // authenticate the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        // retrieve the user
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // retrieve customer or company (depending on the user request)
        var co = request.isCompany() ? companyRepository.findCompanyByEmail(user.getUsername()) : customerRepository.findCustomerByEmail(user.getUsername());
        if (co == null) {
            throw new RuntimeException("User not found");
        }
        if (co instanceof Customer) {
            var customer = (Customer) co;
            fullName= customer.getFullName();
            id = customer.getId();

        }
        else {
            var company = (Company) co;
            fullName = company.getName();
            id = company.getId();
        }
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .email(user.getUsername())
                .id(id)
                .fullName(fullName)
                .token(jwtToken)
                .build();
    }
}

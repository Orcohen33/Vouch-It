package com.vouchit.backend.config;

import com.vouchit.backend.config.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
SecurityConfiguration is a class annotated with @Configuration, @EnableWebSecurity, and @RequiredArgsConstructor that provides security configuration for the application.
 It contains the following method:

1. securityFilterChain: Returns a bean for a SecurityFilterChain object that configures the following security features:
    - Disables CSRF protection
    - Allows all requests to the "/api/v1/auth/" and "/api/v1/coupon/" URL patterns without requiring authentication
    - Requires authentication for all other requests
    - Disables session management and sets the session creation policy to "stateless"
    - Uses the authenticationProvider bean and JwtAuthFilter bean to authenticate requests
This class also contains two private fields, JwtAuthFilter and authenticationProvider, which are injected via the constructor.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter JwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .mvcMatchers("/api/v1/auth/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/coupon/**").permitAll() // allow all to get coupons details
                .mvcMatchers(HttpMethod.POST, "/api/v1/purchase/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

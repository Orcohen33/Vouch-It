package com.vouchit.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
ApplicationConfig is a class annotated with @Configuration and @RequiredArgsConstructor that provides configuration for the application. It contains the following methods:

1. authenticationProvider: Returns a bean for an AuthenticationProvider object that uses the userDetailsService field to retrieve user details and the passwordEncoder bean to encode passwords.
2. authenticationManager: Returns a bean for an AuthenticationManager object using the given AuthenticationConfiguration object.
3. passwordEncoder: Returns a bean for a PasswordEncoder object using the BCryptPasswordEncoder class with a strength of 10.

This class also contains a private field userDetailsService that is injected via the constructor.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

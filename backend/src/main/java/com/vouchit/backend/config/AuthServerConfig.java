//package com.vouchit.backend.config;
//
//import com.vouchit.backend.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//public class AuthServerConfig {
//
//    private final CustomerRepository customerRepository;
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        return http
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .build();
//    }
//
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        return new RegisteredClientRepository() {
//            @Override
//            public void save(RegisteredClient registeredClient) {
//
//            }
//
//            @Override
//            public RegisteredClient findById(String id) {
//                return null;
//            }
//
//            @Override
//            public RegisteredClient findByClientId(String clientId) {
//                return null;
//            }
//        };
//    }
//}

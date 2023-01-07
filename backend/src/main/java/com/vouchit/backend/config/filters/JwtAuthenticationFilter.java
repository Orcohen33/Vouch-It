package com.vouchit.backend.config.filters;

import com.vouchit.backend.config.service.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
JwtAuthenticationFilter is a class that provides a filter for JWT (JSON Web Token) authentication. It performs the following actions:
1. Retrieves the "Authorization" header from the request and checks if it is present and starts with "Bearer ".
 If either of these conditions is not met, the filter chain continues and the method returns.
2. Extracts the token from the header and gets the user email from the token using the jwtService object.
3. Retrieves the user details from the userDetailsService object using the user email and checks if the token is valid using the jwtService object.
4. If the token is valid, it creates an UsernamePasswordAuthenticationToken object using the user details, the user's password, and the user's authorities.
 It then sets the authentication details using a WebAuthenticationDetailsSource object and sets the authentication token in the security context using the SecurityContextHolder.

This class extends OncePerRequestFilter and is annotated with @Component and @RequiredArgsConstructor.
It contains two private fields, jwtService and userDetailsService, which are injected via the constructor.
The doFilterInternal method takes in an HttpServletRequest, HttpServletResponse, and a FilterChain as arguments and
 is responsible for implementing the authentication filter logic described above.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Get the token from the request header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        // Check if the token is null or don't start with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Get the token from the header
        jwt = authHeader.substring(7);
        // Get the user email from the token
        userEmail = jwtService.extractUsername(jwt) ;
        // Check if the user email isn't null and the user is not authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get the user details from the user email
            var userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // Check if the token is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Create the authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                // Set the authentication details
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Set the authentication token to the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

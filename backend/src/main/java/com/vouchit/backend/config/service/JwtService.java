package com.vouchit.backend.config.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/*
JwtService is a class that provides methods for generating and validating JSON Web Tokens (JWTs). It has the following methods:

1. extractUsername: Extracts the subject (username) claim from a JWT.
2. generateAccessToken: Generates an access JWT for the given user details with optional extra claims.
3. generateRefreshToken: Generates a refresh JWT for the given user details with optional extra claims.
4. refreshAccessToken: Refreshes an access JWT using the given refresh JWT and user details.
5. isTokenValid: Validates a JWT by checking if the subject (username) claim matches the given user details and if the token is not expired.
6. getUserFromRefreshToken: Extracts the user details from a refresh JWT.
This class also contains various private helper methods for extracting and decoding claims from a JWT, checking if a JWT is expired, and generating a JWT signing key.
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "3272357538782F413F442A472D4B6150645367566B5970337336763979244226";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(
                Map.of("sub", userDetails.getUsername(), "roles", userDetails.getAuthorities()),
                userDetails
        );
    }
    public String generateAccessToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // expires in 1 hour
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(
                Map.of("sub", userDetails.getUsername(), "roles", userDetails.getAuthorities()),
                userDetails
        );
    }
    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // expires in 1 day
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public String refreshAccessToken(String refreshToken, UserDetails userDetails) {
        final Claims claims = extractAllClaims(refreshToken);
        if (isRefreshTokenExpired(claims)) {
            throw new RuntimeException("Refresh token is expired");
        }
        final String username = claims.getSubject();
        final Map<String, Object> extraClaims = claims.get("extra_claims", Map.class);
        return generateAccessToken(extraClaims, userDetails);
    }

    private boolean isRefreshTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // This method is used to extract the claims from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        System.out.println("token = " + token);
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private byte[] getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes).getEncoded();
    }

    public UserDetails getUserFromRefreshToken(String refreshToken) {
        final Claims claims = extractAllClaims(refreshToken);
        if (isRefreshTokenExpired(claims)) {
            throw new RuntimeException("Refresh token is expired");
        }
        final String username = claims.getSubject();
        final Map<String, Object> extraClaims = claims.get("extra_claims", Map.class);
        return new org.springframework.security.core.userdetails.User(
                username,
                "",
                (List<GrantedAuthority>) extraClaims.get("roles")
        );
    }
}



/*

 */
package com.example.myapplication.models.user;

import androidx.annotation.NonNull;

public class UserDetails {
    private final Long id;
    private String fullName;
    private String email;
    private String accessToken;
    private String refreshToken;

    public UserDetails(Long id, String fullName, String email, String token, String refreshToken) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.accessToken = token;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UserDetails setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserDetails{" + "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + accessToken + '\'' +
                '}';
    }
}

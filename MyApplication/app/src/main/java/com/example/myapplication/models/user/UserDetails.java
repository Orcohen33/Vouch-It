package com.example.myapplication.models.user;

import androidx.annotation.NonNull;

public class UserDetails {
    private final Long id;
    private String fullName;
    private String email;
    private String token;

    public UserDetails(Long id, String fullName, String email, String token) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserDetails{" + "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

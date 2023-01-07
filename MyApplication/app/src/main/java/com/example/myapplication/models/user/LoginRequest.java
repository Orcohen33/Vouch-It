package com.example.myapplication.models.user;

public class LoginRequest {
    private String email;
    private String password;

    private boolean isCompany;

    public LoginRequest(String email, String password, boolean isCompany) {
        this.email = email;
        this.password = password;
        this.isCompany = isCompany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }
}

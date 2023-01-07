package com.example.myapplication.models.user;

public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Boolean isCompany;

    public RegisterRequest(String fullName, String email, String password, Boolean isCompany) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isCompany = isCompany;
    }

    public String fullName() {
        return fullName;
    }

    public RegisterRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String email() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String password() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean isCompany() {
        return isCompany;
    }

    public RegisterRequest setCompany(Boolean company) {
        isCompany = company;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegisterRequest{");
        sb.append("fullName='").append(fullName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", isCompany=").append(isCompany);
        sb.append('}');
        return sb.toString();
    }
}

package com.estudos.estoques.user.api;

public record UserLoginRequest(String email, String rawPassword) {
    public String getEmail() {
        return email;
    }
    public String getRawPassword() {
        return rawPassword;
    }
}

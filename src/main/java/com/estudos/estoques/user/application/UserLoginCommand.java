package com.estudos.estoques.user.application;

public record UserLoginCommand(String email, String rawPassword) {
    public String getEmail() {
        return email;
    }
    public String getRawPassword() {
        return rawPassword;
    }
    
}

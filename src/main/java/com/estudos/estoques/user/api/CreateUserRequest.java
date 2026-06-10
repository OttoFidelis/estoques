package com.estudos.estoques.user.api;

public record CreateUserRequest(String name, String email, String rawPassword) {
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRawPassword() {
        return rawPassword;
    }
}

package com.estudos.estoques.user.application;

public record CreateUserCommand(String name, String email, String rawPassword) {
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

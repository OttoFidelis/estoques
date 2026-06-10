package com.estudos.estoques.user.api;

import com.estudos.estoques.user.domain.User;

public record UserLoginResponse(Long id, String name, String email, String password, String role) {
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    
    public static UserLoginResponse fromDomain(User user) {
        return new UserLoginResponse(
            user.getId().value(),
            user.getName().value(),
            user.getEmail().value(),
            user.getPasswordHash().value(),
            user.getRole().toString()
        );
    }
}

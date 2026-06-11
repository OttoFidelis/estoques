package com.estudos.estoques.user.api;

import com.estudos.estoques.user.domain.User;

public record UserLoginResponse(Long id, String name, String email, String role, String token, String type, Long expiresIn) {
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
    public String getType() {
        return type;
    }
    public Long getExpiresIn() {
        return expiresIn;
    }
    
    public static UserLoginResponse fromDomain(User user, String token, Long expiresIn) {
        return new UserLoginResponse(
            user.getId().value(),
            user.getName().value(),
            user.getEmail().value(),
            user.getRole().toString(),
            token,
            "Bearer",
            expiresIn
        );
    }
}

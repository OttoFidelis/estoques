package com.estudos.estoques.user.api;
import com.estudos.estoques.user.domain.User;

public record CreateUserResponse(String username, String email, String password, String role) {
    public String getUsername() {
        return username;
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
    
    public static CreateUserResponse fromDomain(User user) {
        return new CreateUserResponse(
            user.getName().value(),
            user.getEmail().value(),
            user.getPasswordHash().value(),
            user.getRole().name()
        );
    }
}

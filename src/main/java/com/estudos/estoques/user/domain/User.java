package com.estudos.estoques.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    UserId id;
    UserName name;
    UserEmail email;
    UserPasswordHash passwordHash;
    UserRole role;
    

    public User(UserName name, UserEmail email, UserPasswordHash passwordHash, UserRole role) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}

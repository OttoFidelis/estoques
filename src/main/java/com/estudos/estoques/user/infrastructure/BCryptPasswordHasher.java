package com.estudos.estoques.user.infrastructure;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.estudos.estoques.user.domain.PasswordHasher;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRawPassword;

@Component
public class BCryptPasswordHasher implements PasswordHasher {

    private final PasswordEncoder encoder;

    public BCryptPasswordHasher() {
        this.encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Override
    public UserPasswordHash hashPassword(UserRawPassword rawPassword) {
        String hashed = encoder.encode(rawPassword.getValue());
        return new UserPasswordHash(hashed);
    }

    @Override
    public boolean verifyPassword(UserRawPassword rawPassword, UserPasswordHash hashedPassword) {
        return encoder.matches(rawPassword.getValue(), hashedPassword.getValue());
    }
    
}

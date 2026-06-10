package com.estudos.estoques.user.domain;

public interface PasswordHasher {
    UserPasswordHash hashPassword(UserRawPassword rawPassword);

    boolean verifyPassword(UserRawPassword rawPassword, UserPasswordHash hashedPassword);
}

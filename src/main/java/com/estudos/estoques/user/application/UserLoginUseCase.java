package com.estudos.estoques.user.application;

import org.springframework.stereotype.Service;

import com.estudos.estoques.user.domain.PasswordHasher;
import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRawPassword;
import com.estudos.estoques.user.infrastructure.UserMapper;
import com.estudos.estoques.user.infrastructure.UserRepository;

@Service
public class UserLoginUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserLoginUseCase(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User execute(UserLoginCommand command) {
        var userEntity = userRepository.findByEmail(command.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        var hashedPassword = new UserPasswordHash(userEntity.getPassword());
        if (!passwordHasher.verifyPassword(new UserRawPassword(command.getRawPassword()), hashedPassword)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return UserMapper.toDomain(userEntity);
    }
}

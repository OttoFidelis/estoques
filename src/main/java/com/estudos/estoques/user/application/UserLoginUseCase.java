package com.estudos.estoques.user.application;

import org.springframework.stereotype.Service;

import com.estudos.estoques.user.api.UserLoginResponse;
import com.estudos.estoques.user.domain.PasswordHasher;
import com.estudos.estoques.user.domain.TokenProvider;
import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRawPassword;
import com.estudos.estoques.user.infrastructure.UserMapper;
import com.estudos.estoques.user.infrastructure.UserRepository;

@Service
public class UserLoginUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public UserLoginUseCase(UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    public UserLoginResponse execute(UserLoginCommand command) {
        var userEntity = userRepository.findByEmail(command.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        var hashedPassword = new UserPasswordHash(userEntity.getPassword());
        if (!passwordHasher.verifyPassword(new UserRawPassword(command.getRawPassword()), hashedPassword)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        String token = tokenProvider.generateToken(UserMapper.toDomain(userEntity));
        User user = UserMapper.toDomain(userEntity);
        return UserLoginResponse.fromDomain(
            user,
            token,
            tokenProvider.getExpirationSeconds()
        );
    }
}

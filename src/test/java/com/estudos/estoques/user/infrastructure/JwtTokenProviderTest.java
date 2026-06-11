package com.estudos.estoques.user.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserEmail;
import com.estudos.estoques.user.domain.UserId;
import com.estudos.estoques.user.domain.UserName;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRole;

class JwtTokenProviderTest {

    private final JwtTokenProvider tokenProvider = new JwtTokenProvider();

    @Test
    void shouldGenerateValidateAndReadUserIdFromToken() {
        User user = new User(
                new UserId(1L),
                new UserName("Fulano"),
                new UserEmail("fula@gmail.com"),
                new UserPasswordHash("hashed-password"),
                UserRole.EMPLOYEE
        );

        String token = tokenProvider.generateToken(user);

        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));
        assertEquals(1L, tokenProvider.getUserIdFromToken(token).value());
        assertEquals(3600L, tokenProvider.getExpirationSeconds());
    }
}
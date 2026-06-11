package com.estudos.estoques.user.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserEmail;
import com.estudos.estoques.user.domain.UserId;
import com.estudos.estoques.user.domain.UserName;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRole;

class UserLoginResponseTest {

    @Test
    void shouldMapDomainUserToLoginResponseContract() {
        User user = new User(
                new UserId(1L),
                new UserName("Fulano"),
                new UserEmail("fula@gmail.com"),
                new UserPasswordHash("$2a$10$pWWbMpxtCkwqEnkffrSdEuqukrV3SCThOzP6Ub8dh/8isf/r7pcWW"),
                UserRole.EMPLOYEE
        );

        UserLoginResponse response = UserLoginResponse.fromDomain(user, "token-value", 3600L);

        assertEquals(1L, response.getId());
        assertEquals("Fulano", response.getName());
        assertEquals("fula@gmail.com", response.getEmail());
        assertEquals("EMPLOYEE", response.getRole());
        assertEquals("token-value", response.getToken());
        assertEquals("Bearer", response.getType());
        assertEquals(3600L, response.getExpiresIn());
    }
}
package com.estudos.estoques.user.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserEmail;
import com.estudos.estoques.user.domain.UserId;
import com.estudos.estoques.user.domain.UserName;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRole;

class UserLoginResponseJsonTest {

    @Test
    void shouldMatchExpectedLoginResponseJsonShape() {
    User user = new User(
        new UserId(1L),
        new UserName("Fulano"),
        new UserEmail("fula@gmail.com"),
        new UserPasswordHash("$2a$10$pWWbMpxtCkwqEnkffrSdEuqukrV3SCThOzP6Ub8dh/8isf/r7pcWW"),
        UserRole.EMPLOYEE
    );

    UserLoginResponse response = UserLoginResponse.fromDomain(user, "token-value", 3600L);

    String actualJson = "{" +
        "\"id\":" + response.getId() + "," +
        "\"name\":\"" + response.getName() + "\"," +
        "\"email\":\"" + response.getEmail() + "\"," +
        "\"role\":\"" + response.getRole() + "\"," +
        "\"token\":\"" + response.getToken() + "\"," +
        "\"type\":\"" + response.getType() + "\"," +
        "\"expiresIn\":" + response.getExpiresIn() +
        "}";

    String expectedJson = "{" +
        "\"id\":1," +
        "\"name\":\"Fulano\"," +
        "\"email\":\"fula@gmail.com\"," +
        "\"role\":\"EMPLOYEE\"," +
        "\"token\":\"token-value\"," +
        "\"type\":\"Bearer\"," +
        "\"expiresIn\":3600}";

    assertEquals(expectedJson, actualJson);
    }
}
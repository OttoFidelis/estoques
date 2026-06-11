package com.estudos.estoques.user.domain;

public interface TokenProvider {
    String generateToken(User user);
    boolean validateToken(String token);
    UserId getUserIdFromToken(String token);
    Long getExpirationSeconds();
}

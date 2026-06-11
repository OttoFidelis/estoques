package com.estudos.estoques.user.api;

public record TokenReponse(String token, String type, Long expiresIn) {
    public String getToken() {
        return token;
    }
    public String getType() {
        return type;
    }
    public Long getExpiresIn() {
        return expiresIn;
    }
    
}

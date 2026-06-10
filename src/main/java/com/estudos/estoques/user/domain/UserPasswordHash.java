package com.estudos.estoques.user.domain;

public record UserPasswordHash (String value){
    public UserPasswordHash {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("User password hash cannot be null or blank");
        }
    }

    public String getValue() {
        return value;
    }
}

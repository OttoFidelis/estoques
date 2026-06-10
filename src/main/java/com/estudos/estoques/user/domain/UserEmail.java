package com.estudos.estoques.user.domain;

public record UserEmail(String value) {
    public UserEmail {
    if(value == null || value.isBlank() || !value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("User email cannot be null or blank and must be a valid email address");
        }
    }
    public String getValue() {
        return value;
    }
}

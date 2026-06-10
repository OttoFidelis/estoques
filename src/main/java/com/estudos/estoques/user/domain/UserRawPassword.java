package com.estudos.estoques.user.domain;

public record UserRawPassword(String value) {
    public UserRawPassword {
        if(value == null || value.isBlank() || value.length() < 6 || value.length() < 6 || !value.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$")) {
            throw new IllegalArgumentException("User raw password cannot be null or blank and must be at least 6 characters long and contain at least one letter, one number and special characters");
        }
    }
    public String getValue() {
        return value;
    }
    
}

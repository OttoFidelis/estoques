package com.estudos.estoques.user.domain;

public record UserName(String value) {
  public UserName {
    if (value == null || value.isBlank() || !value.matches("^[A-Za-z\\s]+$")) {
      throw new IllegalArgumentException(
        "User name cannot be null or blank and must contain only letters and spaces"
      );
    }
  }

  public String getValue() {
    return value;
  }
}

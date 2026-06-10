package com.estudos.estoques.product.domain;

public record ProductDescription(String value) {
  public ProductDescription {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(
        "Product description cannot be null or blank"
      );
    }
  }

  public String getValue() {
    return value;
  }
}

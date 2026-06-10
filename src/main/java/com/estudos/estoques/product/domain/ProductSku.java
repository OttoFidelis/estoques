package com.estudos.estoques.product.domain;

public record ProductSku(String value) {
    public ProductSku {
        if (value == null || value.isBlank() || value.length() > 255) {
            throw new IllegalArgumentException("Product SKU cannot be null or blank");
        }
    }
    public String getValue() {
        return value;
    }
}
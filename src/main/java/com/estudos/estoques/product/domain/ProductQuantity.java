package com.estudos.estoques.product.domain;

public record ProductQuantity(Integer value) {
    public ProductQuantity {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Product quantity cannot be null or negative");
        }
    }
    public Integer getValue() {
        return value;
    }
}
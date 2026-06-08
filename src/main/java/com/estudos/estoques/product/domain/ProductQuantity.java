package com.estudos.estoques.product.domain;

public record ProductQuantity(Integer value) {
    public Integer getValue() {
        return value;
    }
}
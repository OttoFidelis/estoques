package com.estudos.estoques.product.domain;

public record ProductId(Long value) {
    public Long getValue() {
        return value;
    }
}

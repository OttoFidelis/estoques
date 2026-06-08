package com.estudos.estoques.stockMovement.domain;

public record MovementId(Long value) {
    public Long getValue() {
        return value;
    }
}

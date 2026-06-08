package com.estudos.estoques.stockMovement.application;

import java.time.LocalDateTime;

import com.estudos.estoques.product.infrastructure.ProductEntity;

public record CreateMovementCommand(
    Long id,
    Integer quantity,
    int type,
    ProductEntity product,
    LocalDateTime ocurredAt
) {
    public Long getId() {
        return id;
    }
    public ProductEntity getProduct() {
        return product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public int getType() {
        return type;
    }
    public LocalDateTime getOcurredAt() {
        return ocurredAt;
    }
}
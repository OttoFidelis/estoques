package com.estudos.estoques.stockMovement.api;

import java.time.LocalDateTime;

import com.estudos.estoques.product.infrastructure.ProductEntity;

public record CreateMovementRequest(
    Integer quantity,
    ProductEntity product,
    LocalDateTime ocurredAt
) {
    public ProductEntity getProduct() {
        return product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public LocalDateTime getOcurredAt() {
        return ocurredAt;
    }
}
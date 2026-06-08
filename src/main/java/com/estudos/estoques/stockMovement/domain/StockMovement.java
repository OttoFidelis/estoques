package com.estudos.estoques.stockMovement.domain;

import java.time.LocalDateTime;

import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.product.infrastructure.ProductEntity;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class StockMovement {
    private MovementId id;
    private ProductEntity product;
    private MovementType movementType;
    private ProductQuantity quantity;
    private LocalDateTime ocurredAt;
}

package com.estudos.estoques.stockMovement.api;

import com.estudos.estoques.stockMovement.domain.StockMovement;

public record FindByIdMovementResponse(
    Long id,
    Integer quantity,
    String type,
    String productName,
    String ocurredAt
) {
    public static FindByIdMovementResponse fromDomain(StockMovement movement) {
        return new FindByIdMovementResponse(
            movement.getId().value(),
            movement.getQuantity().value(),
            movement.getMovementType().toString(),
            movement.getProduct().getName(),
            movement.getOcurredAt().toString()
        );
    }
}

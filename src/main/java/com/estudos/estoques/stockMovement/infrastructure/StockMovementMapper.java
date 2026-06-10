package com.estudos.estoques.stockMovement.infrastructure;

import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.stockMovement.domain.MovementId;
import com.estudos.estoques.stockMovement.domain.MovementType;
import com.estudos.estoques.stockMovement.domain.OcurredAt;
import com.estudos.estoques.stockMovement.domain.StockMovement;

public class StockMovementMapper {
    public static StockMovementEntity toEntity(StockMovement stockMovement) {
        StockMovementEntity entity = new StockMovementEntity();
        entity.setProduct(stockMovement.getProduct());
        entity.setMovementType(stockMovement.getMovementType().name());
        entity.setQuantity(stockMovement.getQuantity().getValue());
        entity.setOcurredAt(stockMovement.getOcurredAt().getValue());
        return entity;
    }

    public static StockMovement toDomain(StockMovementEntity entity) {
        StockMovement stockMovement = new StockMovement(
            new MovementId(entity.getId()),
            entity.getProduct(),
            MovementType.valueOf(entity.getMovementType()),
            new ProductQuantity(entity.getQuantity()),
            new OcurredAt(entity.getOcurredAt())
        );
        return stockMovement;
    }
}

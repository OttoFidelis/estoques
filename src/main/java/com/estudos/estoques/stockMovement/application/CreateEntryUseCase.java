package com.estudos.estoques.stockMovement.application;
import org.springframework.stereotype.Service;

import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.stockMovement.domain.MovementType;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementRepository;
import com.estudos.estoques.stockMovement.domain.StockMovement;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementMapper;

@Service
public class CreateEntryUseCase {
    private final StockMovementRepository stockMovementRepository;

    public CreateEntryUseCase(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public void execute(CreateMovementCommand command){
        StockMovement movement = new StockMovement(
            null,
            command.getProduct(),
            MovementType.ENTRY,
            new ProductQuantity(command.getQuantity()),
            command.getOcurredAt()
        );
        stockMovementRepository.save(StockMovementMapper.toEntity(movement));
    }
}

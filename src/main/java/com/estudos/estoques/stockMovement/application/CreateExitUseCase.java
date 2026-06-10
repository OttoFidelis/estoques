package com.estudos.estoques.stockMovement.application;

import org.springframework.stereotype.Service;

import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductRepository;
import com.estudos.estoques.stockMovement.domain.MovementType;
import com.estudos.estoques.stockMovement.domain.OcurredAt;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementRepository;
import com.estudos.estoques.stockMovement.domain.StockMovement;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementMapper;

@Service
public class CreateExitUseCase {
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public CreateExitUseCase(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;

    }

    public String execute(CreateMovementCommand command){
        StockMovement movement = new StockMovement(
            null,
            command.getProduct(),
            MovementType.EXIT,
            new ProductQuantity(command.getQuantity()),
            new OcurredAt(command.getOcurredAt())
        );
        ProductEntity productEntity = productRepository.findById(command.getProduct().getId()).orElseThrow();
        int oldStock = productEntity.getQuantity();
        int exit = command.getQuantity();
        int newStock = oldStock - exit;
        productEntity.setQuantity(newStock);
        productRepository.save(productEntity);
        movement.setProduct(productEntity);
        stockMovementRepository.save(StockMovementMapper.toEntity(movement));
        return "Old stock: " + oldStock + "\nExit: " + exit + ",\nNew stock: " + newStock;
    }
}

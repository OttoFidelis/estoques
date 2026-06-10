package com.estudos.estoques.stockMovement.application;
import org.springframework.stereotype.Service;

import com.estudos.estoques.product.domain.Product;
import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductMapper;
import com.estudos.estoques.product.infrastructure.ProductRepository;
import com.estudos.estoques.stockMovement.domain.MovementType;
import com.estudos.estoques.stockMovement.domain.OcurredAt;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementRepository;
import com.estudos.estoques.stockMovement.domain.StockMovement;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementMapper;

@Service
public class CreateEntryUseCase {
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public CreateEntryUseCase(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    public String execute(CreateMovementCommand command){
        StockMovement movement = new StockMovement(
            null,
            command.getProduct(),
            MovementType.ENTRY,
            new ProductQuantity(command.getQuantity()),
            new OcurredAt(command.getOcurredAt())
        );
        ProductEntity productEntity = productRepository.findById(command.getProduct().getId()).orElseThrow();
        Product product = ProductMapper.toDomain(productEntity);
        int oldStock = productEntity.getQuantity();
        int entry = command.getQuantity();
        int newStock = oldStock + entry;
        product.setQuantity(new ProductQuantity(newStock));
        productEntity = ProductMapper.toEntity(product);
        productRepository.save(productEntity);
        movement.setProduct(productEntity);
        stockMovementRepository.save(StockMovementMapper.toEntity(movement));
        return "Old stock: " + oldStock + "\nEntry: " + entry + ",\nNew stock: " + newStock;
    }
}

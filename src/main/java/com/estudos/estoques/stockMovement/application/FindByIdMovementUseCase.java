package com.estudos.estoques.stockMovement.application;

import org.springframework.stereotype.Service;

import com.estudos.estoques.stockMovement.infrastructure.StockMovementEntity;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementRepository;

@Service
public class FindByIdMovementUseCase {
    private final StockMovementRepository stockMovementRepository;

    public FindByIdMovementUseCase(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public StockMovementEntity execute(Long id) {
        return stockMovementRepository.findById(id).orElseThrow();
    }
}

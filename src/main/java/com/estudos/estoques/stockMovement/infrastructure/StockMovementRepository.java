package com.estudos.estoques.stockMovement.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    
}

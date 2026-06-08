package com.estudos.estoques.stockMovement.infrastructure;

import java.time.LocalDateTime;

import com.estudos.estoques.product.infrastructure.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stock_movement")
public class StockMovementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private String movementType;

    private int quantity;

    private LocalDateTime ocurredAt;

}

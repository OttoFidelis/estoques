package com.estudos.estoques.product.application;

import java.util.Optional;

import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductRepository;

public class FindByIdProductUseCase {
    private final ProductRepository productRepository;

    public FindByIdProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductEntity> execute(Long id) {
        return productRepository.findById(id);
    }
}

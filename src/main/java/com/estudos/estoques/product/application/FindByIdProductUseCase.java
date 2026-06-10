package com.estudos.estoques.product.application;

import org.springframework.stereotype.Service;

import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductRepository;

@Service
public class FindByIdProductUseCase {
    private final ProductRepository productRepository;

    public FindByIdProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity execute(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}

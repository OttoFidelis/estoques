package com.estudos.estoques.product.application;

import com.estudos.estoques.product.infrastructure.ProductRepository;

import org.springframework.stereotype.Service;

import com.estudos.estoques.product.domain.Product;
import com.estudos.estoques.product.domain.ProductName;
import com.estudos.estoques.product.domain.ProductDescription;
import com.estudos.estoques.product.domain.ProductSku;
import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductMapper;

@Service
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(CreateProductCommand command) {
        Product product = new Product(
            new ProductName(command.getName()),
            new ProductDescription(command.getDescription()),
            new ProductSku(command.getSku()),
            new ProductQuantity(command.getQuantity()),
            new ProductQuantity(command.getMinQuantity())
        );
        ProductEntity productEntity = ProductMapper.toEntity(product);
        productRepository.save(productEntity);
        return product;
    }
}
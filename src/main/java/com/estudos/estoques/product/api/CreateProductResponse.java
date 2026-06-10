package com.estudos.estoques.product.api;

import com.estudos.estoques.product.domain.Product;

public record CreateProductResponse(
    String name,
    String description,
    String sku,
    Integer quantity
) {

    public static CreateProductResponse fromDomain(Product product) {
        return new CreateProductResponse(
            product.getName().value(),
            product.getDescription().value(),
            product.getSku().value(),
            product.getQuantity().value()
        );
    }
}
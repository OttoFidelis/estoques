package com.estudos.estoques.product.api;

import com.estudos.estoques.product.domain.Product;

public record FindByIdProductResponse(
    Long id,
    String name,
    String description,
    String sku,
    Integer quantity
) {

    public static FindByIdProductResponse fromDomain(Product product) {
        return new FindByIdProductResponse(
            product.getId().value(),
            product.getName().value(),
            product.getDescription().value(),
            product.getSku().value(),
            product.getQuantity().value()
        );
    }
}
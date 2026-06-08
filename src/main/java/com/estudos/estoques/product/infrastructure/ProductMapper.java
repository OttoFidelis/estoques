package com.estudos.estoques.product.infrastructure;

import com.estudos.estoques.product.domain.Product;
import com.estudos.estoques.product.domain.ProductDescription;
import com.estudos.estoques.product.domain.ProductId;
import com.estudos.estoques.product.domain.ProductName;
import com.estudos.estoques.product.domain.ProductQuantity;
import com.estudos.estoques.product.domain.ProductSku;

public class ProductMapper {
    
    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId() != null ? product.getId().getValue() : null);
        entity.setName(product.getName().getValue());
        entity.setDescription(product.getDescription().getValue());
        entity.setSku(product.getSku().getValue());
        entity.setQuantity(product.getQuantity().getValue());
        entity.setMinQuantity(product.getMinQuantity().getValue());
        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                new ProductId(entity.getId()),
                new ProductName(entity.getName()),
                new ProductDescription(entity.getDescription()),
                new ProductSku(entity.getSku()),
                new ProductQuantity(entity.getQuantity()),
                new ProductQuantity(entity.getMinQuantity())
        );
    }
}
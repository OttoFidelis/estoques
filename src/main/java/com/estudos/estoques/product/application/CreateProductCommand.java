package com.estudos.estoques.product.application;

public record CreateProductCommand(
    String name,
    String description,
    String sku,
    Integer quantity,
    Integer minQuantity
) {
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getSku() {
        return sku;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Integer getMinQuantity() {
        return minQuantity;
    }
}

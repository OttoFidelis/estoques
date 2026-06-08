package com.estudos.estoques.product.application;

public record CreateProductCommand(Long id,
    String name,
    String description,
    String sku,
    Integer quantity,
    Integer minQuantity
) {
    public Long getId() {
        return id;
    }
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

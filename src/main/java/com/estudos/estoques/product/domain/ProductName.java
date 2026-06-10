package com.estudos.estoques.product.domain;

public record ProductName(String value) {  
    public ProductName {
        if (value == null || value.isBlank() || value.length() > 255) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
    } 
    public String getValue() {
        return value;
    }
}
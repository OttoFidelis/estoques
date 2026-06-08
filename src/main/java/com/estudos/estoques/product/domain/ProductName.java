package com.estudos.estoques.product.domain;

public record ProductName(String value) {   
    public String getValue() {
        return value;
    }
}
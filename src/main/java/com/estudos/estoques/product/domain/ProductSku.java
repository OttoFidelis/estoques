package com.estudos.estoques.product.domain;

public record ProductSku(String value) {
    public String getValue() {
        return value;
    }
}
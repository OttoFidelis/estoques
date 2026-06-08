package com.estudos.estoques.product.domain;

public record ProductDescription(String value) {

    public String getValue() {
        return value;
    }

}
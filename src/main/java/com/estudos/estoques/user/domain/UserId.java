package com.estudos.estoques.user.domain;

public record UserId (Long value){
    public Long getValue() {
        return value;
    }
}

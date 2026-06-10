package com.estudos.estoques.stockMovement.domain;

import java.time.LocalDateTime;

public record OcurredAt(LocalDateTime value) {
    public OcurredAt {
        if (value == null || value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("OcurredAt cannot be null or in the future");
        }
    }
    public LocalDateTime getValue() {
        return value;
    }
    
}

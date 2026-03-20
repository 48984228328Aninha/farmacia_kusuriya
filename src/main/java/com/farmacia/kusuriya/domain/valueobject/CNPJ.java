package com.farmacia.kusuriya.domain.valueobject;

import java.util.Objects;

public class CNPJ {
    
    private final String valor;

    public CNPJ(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
        }
        if (valor.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj = (CNPJ) o;
        return Objects.equals(valor, cnpj.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}

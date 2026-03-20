package com.farmacia.kusuriya.domain.valueobject;

import java.util.Objects;

public class CPF {
    
    private final String valor;

    public CPF(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (valor.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
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
        CPF cpf = (CPF) o;
        return Objects.equals(valor, cpf.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}

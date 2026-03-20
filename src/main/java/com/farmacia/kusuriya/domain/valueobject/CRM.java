package com.farmacia.kusuriya.domain.valueobject;

import java.util.Objects;

public class CRM {
    
    private final String numero;
    private final String estado;

    public CRM(String numero, String estado) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número do CRM não pode ser nulo ou vazio");
        }
        if (estado == null || estado.length() != 2) {
            throw new IllegalArgumentException("Estado deve conter 2 caracteres");
        }
        this.numero = numero;
        this.estado = estado.toUpperCase();
    }

    public String getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CRM crm = (CRM) o;
        return Objects.equals(numero, crm.numero) && Objects.equals(estado, crm.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, estado);
    }
}

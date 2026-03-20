package com.farmacia.kusuriya.domain.valueobject;

import java.util.Objects;

public class Endereco {
    
    private final String rua;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final String cep;

    public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        if (rua == null || rua.isBlank()) {
            throw new IllegalArgumentException("Rua não pode ser nula ou vazia");
        }
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número não pode ser nulo ou vazio");
        }
        if (bairro == null || bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro não pode ser nulo ou vazio");
        }
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade não pode ser nula ou vazia");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado não pode ser nulo ou vazio");
        }
        if (cep == null || cep.length() != 8) {
            throw new IllegalArgumentException("CEP deve conter 8 dígitos");
        }
        
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(rua, endereco.rua) &&
               Objects.equals(numero, endereco.numero) &&
               Objects.equals(bairro, endereco.bairro) &&
               Objects.equals(cidade, endereco.cidade) &&
               Objects.equals(estado, endereco.estado) &&
               Objects.equals(cep, endereco.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua, numero, bairro, cidade, estado, cep);
    }
}

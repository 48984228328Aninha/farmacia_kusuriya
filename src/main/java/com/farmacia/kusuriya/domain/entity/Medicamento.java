package com.farmacia.kusuriya.domain.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicamento")
public class Medicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String principioAtivo;
    
    @Column(nullable = false)
    private String dosagem;
    
    @Column(nullable = false)
    private boolean controlado;

    protected Medicamento() {}

    public Medicamento(String nome, String principioAtivo, String dosagem, boolean controlado) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (principioAtivo == null || principioAtivo.isBlank()) {
            throw new IllegalArgumentException("Princípio ativo não pode ser nulo ou vazio");
        }
        if (dosagem == null || dosagem.isBlank()) {
            throw new IllegalArgumentException("Dosagem não pode ser nula ou vazia");
        }
        
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.dosagem = dosagem;
        this.controlado = controlado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public String getDosagem() {
        return dosagem;
    }

    public boolean isControlado() {
        return controlado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicamento that = (Medicamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

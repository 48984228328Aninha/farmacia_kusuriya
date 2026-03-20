package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CPF;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paciente")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "cpf", unique = true, nullable = false))
    private CPF cpf;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String telefone;
    
    @Embedded
    private Endereco endereco;

    protected Paciente() {}

    public Paciente(String nome, CPF cpf, String email, String telefone, Endereco endereco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cpf, paciente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

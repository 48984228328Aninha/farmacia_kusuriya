package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "farmacia")
public class Farmacia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "cnpj", unique = true, nullable = false))
    private CNPJ cnpj;
    
    @Embedded
    private Endereco endereco;
    
    @Column(nullable = false)
    private String telefone;
    
    @Column(nullable = false)
    private String email;
    
    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;
    
    @Column(nullable = false)
    private boolean ativa;

    protected Farmacia() {}

    public Farmacia(String nome, CNPJ cnpj, Endereco endereco, String telefone, String email, String horarioFuncionamento) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo");
        }
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.horarioFuncionamento = horarioFuncionamento;
        this.ativa = true;
    }

    public void inativar() {
        this.ativa = false;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public boolean isAtiva() {
        return ativa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmacia farmacia = (Farmacia) o;
        return Objects.equals(cnpj, farmacia.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}

package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CRM;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medico")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "numero", column = @Column(name = "crm_numero", nullable = false)),
        @AttributeOverride(name = "estado", column = @Column(name = "crm_estado", nullable = false))
    })
    private CRM crm;
    
    @Column(nullable = false)
    private String especialidade;
    
    private String telefone;
    
    private String email;

    protected Medico() {}

    public Medico(String nome, CRM crm, String especialidade) {
        this(nome, crm, especialidade, null, null);
    }

    public Medico(String nome, CRM crm, String especialidade, String telefone, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (crm == null) {
            throw new IllegalArgumentException("CRM não pode ser nulo");
        }
        if (especialidade == null || especialidade.isBlank()) {
            throw new IllegalArgumentException("Especialidade não pode ser nula ou vazia");
        }
        
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CRM getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(crm, medico.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crm);
    }
}

package com.farmacia.kusuriya.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "receita")
public class Receita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;
    
    @Column(nullable = false)
    private String urlArquivo;
    
    @Column(nullable = false)
    private LocalDateTime dataEmissao;
    
    @Column(nullable = false)
    private boolean validada;
    
    @Column(name = "data_validacao")
    private LocalDateTime dataValidacao;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    protected Receita() {}

    public Receita(Paciente paciente, Medico medico, String urlArquivo, LocalDateTime dataEmissao) {
        this(paciente, medico, urlArquivo, dataEmissao, null);
    }

    public Receita(Paciente paciente, Medico medico, String urlArquivo, LocalDateTime dataEmissao, String observacoes) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não pode ser nulo");
        }
        if (medico == null) {
            throw new IllegalArgumentException("Médico não pode ser nulo");
        }
        if (urlArquivo == null || urlArquivo.isBlank()) {
            throw new IllegalArgumentException("URL do arquivo não pode ser nula ou vazia");
        }
        if (dataEmissao == null) {
            throw new IllegalArgumentException("Data de emissão não pode ser nula");
        }
        
        this.paciente = paciente;
        this.medico = medico;
        this.urlArquivo = urlArquivo;
        this.dataEmissao = dataEmissao;
        this.observacoes = observacoes;
        this.validada = false;
    }

    public void validar() {
        if (this.validada) {
            throw new IllegalStateException("Receita já foi validada");
        }
        this.validada = true;
        this.dataValidacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public boolean isValidada() {
        return validada;
    }

    public LocalDateTime getDataValidacao() {
        return dataValidacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(id, receita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.enums.StatusPedido;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class PedidoMedicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "receita_id", nullable = false)
    private Receita receita;
    
    @ManyToMany
    @JoinTable(
        name = "pedido_medicamento",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    protected PedidoMedicamento() {}

    public PedidoMedicamento(Paciente paciente, Receita receita, List<Medicamento> medicamentos) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não pode ser nulo");
        }
        if (receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        if (!receita.isValidada()) {
            throw new IllegalStateException("Receita deve estar validada");
        }
        if (medicamentos == null || medicamentos.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter ao menos um medicamento");
        }
        
        this.paciente = paciente;
        this.receita = receita;
        this.medicamentos = new ArrayList<>(medicamentos);
        this.status = StatusPedido.CRIADO;
        this.dataCriacao = LocalDateTime.now();
    }

    public void confirmar() {
        if (status != StatusPedido.CRIADO) {
            throw new IllegalStateException("Apenas pedidos criados podem ser confirmados");
        }
        this.status = StatusPedido.CONFIRMADO;
    }

    public void separar() {
        if (status != StatusPedido.CONFIRMADO) {
            throw new IllegalStateException("Apenas pedidos confirmados podem ser separados");
        }
        this.status = StatusPedido.SEPARADO;
    }

    public void cancelar() {
        if (status == StatusPedido.SEPARADO) {
            throw new IllegalStateException("Pedidos separados não podem ser cancelados");
        }
        this.status = StatusPedido.CANCELADO;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Receita getReceita() {
        return receita;
    }

    public List<Medicamento> getMedicamentos() {
        return new ArrayList<>(medicamentos);
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoMedicamento that = (PedidoMedicamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

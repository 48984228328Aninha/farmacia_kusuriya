package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.enums.StatusEntrega;
import com.farmacia.kusuriya.domain.enums.TipoEntrega;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "entrega")
public class Entrega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private PedidoMedicamento pedido;
    
    @Embedded
    private Endereco enderecoEntrega;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEntrega tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntrega status;
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    
    private LocalDateTime dataEntrega;

    protected Entrega() {}

    public Entrega(PedidoMedicamento pedido, Endereco enderecoEntrega, TipoEntrega tipo) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        if (enderecoEntrega == null) {
            throw new IllegalArgumentException("Endereço de entrega não pode ser nulo");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de entrega não pode ser nulo");
        }
        
        this.pedido = pedido;
        this.enderecoEntrega = enderecoEntrega;
        this.tipo = tipo;
        this.status = StatusEntrega.CRIADA;
        this.dataCriacao = LocalDateTime.now();
    }

    public void iniciarTransporte() {
        if (status != StatusEntrega.CRIADA) {
            throw new IllegalStateException("Apenas entregas criadas podem iniciar transporte");
        }
        this.status = StatusEntrega.EM_TRANSPORTE;
    }

    public void finalizar() {
        if (status != StatusEntrega.EM_TRANSPORTE) {
            throw new IllegalStateException("Apenas entregas em transporte podem ser finalizadas");
        }
        this.status = StatusEntrega.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }

    public void cancelar() {
        if (status == StatusEntrega.ENTREGUE) {
            throw new IllegalStateException("Entregas finalizadas não podem ser canceladas");
        }
        this.status = StatusEntrega.CANCELADA;
    }

    public Long getId() {
        return id;
    }

    public PedidoMedicamento getPedido() {
        return pedido;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public TipoEntrega getTipo() {
        return tipo;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(id, entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

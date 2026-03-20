package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.PedidoMedicamento;
import com.farmacia.kusuriya.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCase {
    
    private final PedidoRepository pedidoRepository;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoMedicamento executar(PedidoMedicamento pedido) {
        return pedidoRepository.save(pedido);
    }
}

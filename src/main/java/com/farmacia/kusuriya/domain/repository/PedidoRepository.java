package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.PedidoMedicamento;
import java.util.Optional;

public interface PedidoRepository {
    
    PedidoMedicamento save(PedidoMedicamento pedido);
    
    Optional<PedidoMedicamento> findById(Long id);
}

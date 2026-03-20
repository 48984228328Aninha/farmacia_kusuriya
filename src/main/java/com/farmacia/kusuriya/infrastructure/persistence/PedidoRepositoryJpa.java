package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.PedidoMedicamento;
import com.farmacia.kusuriya.domain.repository.PedidoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositoryJpa extends JpaRepository<PedidoMedicamento, Long>, PedidoRepository {
}

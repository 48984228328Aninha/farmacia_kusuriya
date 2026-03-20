package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Entrega;
import com.farmacia.kusuriya.domain.repository.EntregaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositoryJpa extends JpaRepository<Entrega, Long>, EntregaRepository {
}

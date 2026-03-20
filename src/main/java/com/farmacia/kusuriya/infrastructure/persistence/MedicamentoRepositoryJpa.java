package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Medicamento;
import com.farmacia.kusuriya.domain.repository.MedicamentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepositoryJpa extends JpaRepository<Medicamento, Long>, MedicamentoRepository {
}

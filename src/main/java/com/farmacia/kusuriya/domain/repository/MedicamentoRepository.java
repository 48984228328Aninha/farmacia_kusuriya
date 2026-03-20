package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Medicamento;
import java.util.Optional;

public interface MedicamentoRepository {
    
    Medicamento save(Medicamento medicamento);
    
    Optional<Medicamento> findById(Long id);
}

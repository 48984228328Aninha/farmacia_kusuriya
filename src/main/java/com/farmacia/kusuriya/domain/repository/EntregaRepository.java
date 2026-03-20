package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Entrega;
import java.util.Optional;

public interface EntregaRepository {
    
    Entrega save(Entrega entrega);
    
    Optional<Entrega> findById(Long id);
}

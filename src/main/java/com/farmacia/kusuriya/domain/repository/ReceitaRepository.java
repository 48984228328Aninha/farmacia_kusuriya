package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Receita;
import java.util.Optional;

public interface ReceitaRepository {
    
    Receita save(Receita receita);
    
    Optional<Receita> findById(Long id);
}

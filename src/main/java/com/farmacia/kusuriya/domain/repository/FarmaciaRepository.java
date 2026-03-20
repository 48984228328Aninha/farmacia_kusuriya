package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import java.util.List;
import java.util.Optional;

public interface FarmaciaRepository {
    
    Farmacia save(Farmacia farmacia);
    
    Optional<Farmacia> findById(Long id);
    
    Optional<Farmacia> findByCnpj(CNPJ cnpj);
    
    boolean existsByCnpj(CNPJ cnpj);
    
    List<Farmacia> findAll();
    
    List<Farmacia> findAllAtivas();
}

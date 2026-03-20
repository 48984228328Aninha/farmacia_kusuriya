package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    
    Medico save(Medico medico);
    
    Optional<Medico> findById(Long id);
    
    Optional<Medico> findByCrm(CRM crm);
    
    boolean existsByCrm(CRM crm);
    
    List<Medico> findAll();
    
    List<Medico> findByEspecialidade(String especialidade);
}

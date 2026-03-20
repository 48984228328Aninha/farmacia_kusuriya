package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.repository.MedicoRepository;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepositoryJpa extends JpaRepository<Medico, Long>, MedicoRepository {
    
    Optional<Medico> findByCrm(CRM crm);
    
    boolean existsByCrm(CRM crm);
    
    List<Medico> findByEspecialidade(String especialidade);
}

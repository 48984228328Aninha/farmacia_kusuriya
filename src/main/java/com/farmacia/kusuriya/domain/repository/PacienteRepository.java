package com.farmacia.kusuriya.domain.repository;

import com.farmacia.kusuriya.domain.entity.Paciente;
import com.farmacia.kusuriya.domain.valueobject.CPF;
import java.util.Optional;

public interface PacienteRepository {
    
    Paciente save(Paciente paciente);
    
    Optional<Paciente> findById(Long id);
    
    Optional<Paciente> findByCpf(CPF cpf);
    
    boolean existsByCpf(CPF cpf);
}

package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Paciente;
import com.farmacia.kusuriya.domain.repository.PacienteRepository;
import com.farmacia.kusuriya.domain.valueobject.CPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepositoryJpa extends JpaRepository<Paciente, Long>, PacienteRepository {
    
    Optional<Paciente> findByCpf(CPF cpf);
    
    boolean existsByCpf(CPF cpf);
}

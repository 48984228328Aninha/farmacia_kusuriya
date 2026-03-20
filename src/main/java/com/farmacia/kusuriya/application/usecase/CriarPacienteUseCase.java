package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Paciente;
import com.farmacia.kusuriya.domain.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarPacienteUseCase {
    
    private final PacienteRepository pacienteRepository;

    public CriarPacienteUseCase(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente executar(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new IllegalStateException("Paciente já cadastrado com este CPF");
        }
        return pacienteRepository.save(paciente);
    }
}

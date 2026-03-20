package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarMedicoUseCase {
    
    private final MedicoRepository medicoRepository;

    public CriarMedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico executar(Medico medico) {
        if (medicoRepository.existsByCrm(medico.getCrm())) {
            throw new IllegalStateException("Médico já cadastrado com este CRM");
        }
        return medicoRepository.save(medico);
    }
}

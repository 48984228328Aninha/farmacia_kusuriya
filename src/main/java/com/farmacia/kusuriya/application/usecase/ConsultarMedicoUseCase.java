package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.repository.MedicoRepository;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultarMedicoUseCase {
    
    private final MedicoRepository medicoRepository;

    public ConsultarMedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
    }

    public Medico buscarPorCrm(CRM crm) {
        return medicoRepository.findByCrm(crm)
            .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public List<Medico> listarPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }
}

package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Entrega;
import com.farmacia.kusuriya.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarEntregaUseCase {
    
    private final EntregaRepository entregaRepository;

    public CriarEntregaUseCase(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega executar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }
}

package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.repository.FarmaciaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultarFarmaciaUseCase {
    
    private final FarmaciaRepository farmaciaRepository;

    public ConsultarFarmaciaUseCase(FarmaciaRepository farmaciaRepository) {
        this.farmaciaRepository = farmaciaRepository;
    }

    public Farmacia buscarPorId(Long id) {
        return farmaciaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Farmácia não encontrada"));
    }

    public List<Farmacia> listarTodas() {
        return farmaciaRepository.findAll();
    }

    public List<Farmacia> listarAtivas() {
        return farmaciaRepository.findAllAtivas();
    }
}

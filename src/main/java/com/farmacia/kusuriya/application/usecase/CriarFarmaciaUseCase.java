package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.repository.FarmaciaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarFarmaciaUseCase {
    
    private final FarmaciaRepository farmaciaRepository;

    public CriarFarmaciaUseCase(FarmaciaRepository farmaciaRepository) {
        this.farmaciaRepository = farmaciaRepository;
    }

    public Farmacia executar(Farmacia farmacia) {
        if (farmaciaRepository.existsByCnpj(farmacia.getCnpj())) {
            throw new IllegalStateException("Farmácia já cadastrada com este CNPJ");
        }
        return farmaciaRepository.save(farmacia);
    }
}

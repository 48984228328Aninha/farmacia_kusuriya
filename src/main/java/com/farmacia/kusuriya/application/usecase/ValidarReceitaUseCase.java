package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Receita;
import com.farmacia.kusuriya.domain.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidarReceitaUseCase {
    
    private final ReceitaRepository receitaRepository;

    public ValidarReceitaUseCase(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita executar(Long receitaId) {
        Receita receita = receitaRepository.findById(receitaId)
            .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
        
        if (receita.isValidada()) {
            throw new IllegalStateException("Receita já foi validada");
        }
        
        receita.validar();
        return receitaRepository.save(receita);
    }
}

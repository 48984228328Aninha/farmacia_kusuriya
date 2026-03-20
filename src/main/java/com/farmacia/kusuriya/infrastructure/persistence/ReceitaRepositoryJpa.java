package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Receita;
import com.farmacia.kusuriya.domain.repository.ReceitaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepositoryJpa extends JpaRepository<Receita, Long>, ReceitaRepository {
}

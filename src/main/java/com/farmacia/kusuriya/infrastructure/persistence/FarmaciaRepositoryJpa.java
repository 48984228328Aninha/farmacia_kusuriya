package com.farmacia.kusuriya.infrastructure.persistence;

import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.repository.FarmaciaRepository;
import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmaciaRepositoryJpa extends JpaRepository<Farmacia, Long>, FarmaciaRepository {
    
    Optional<Farmacia> findByCnpj(CNPJ cnpj);
    
    boolean existsByCnpj(CNPJ cnpj);
    
    @Query("SELECT f FROM Farmacia f WHERE f.ativa = true")
    List<Farmacia> findAllAtivas();
}

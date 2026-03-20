package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.repository.FarmaciaRepository;
import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarFarmaciaUseCaseTest {

    @Mock
    private FarmaciaRepository farmaciaRepository;

    @InjectMocks
    private CriarFarmaciaUseCase criarFarmaciaUseCase;

    @Test
    void deveCriarFarmaciaComSucesso() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        Farmacia farmacia = new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h");

        when(farmaciaRepository.existsByCnpj(cnpj)).thenReturn(false);
        when(farmaciaRepository.save(any(Farmacia.class))).thenReturn(farmacia);

        Farmacia resultado = criarFarmaciaUseCase.executar(farmacia);

        assertNotNull(resultado);
        assertEquals("Farmácia Central", resultado.getNome());
        verify(farmaciaRepository, times(1)).save(any(Farmacia.class));
    }

    @Test
    void deveRejeitarFarmaciaDuplicada() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        Farmacia farmacia = new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h");

        when(farmaciaRepository.existsByCnpj(cnpj)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> criarFarmaciaUseCase.executar(farmacia));
        verify(farmaciaRepository, never()).save(any(Farmacia.class));
    }
}

package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.repository.MedicoRepository;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarMedicoUseCaseTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private CriarMedicoUseCase criarMedicoUseCase;

    @Test
    void deveCriarMedicoComSucesso() {
        CRM crm = new CRM("123456", "SP");
        Medico medico = new Medico("Dr. João Silva", crm, "Cardiologia");

        when(medicoRepository.existsByCrm(crm)).thenReturn(false);
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        Medico resultado = criarMedicoUseCase.executar(medico);

        assertNotNull(resultado);
        assertEquals("Dr. João Silva", resultado.getNome());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void deveRejeitarMedicoDuplicado() {
        CRM crm = new CRM("123456", "SP");
        Medico medico = new Medico("Dr. João Silva", crm, "Cardiologia");

        when(medicoRepository.existsByCrm(crm)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> criarMedicoUseCase.executar(medico));
        verify(medicoRepository, never()).save(any(Medico.class));
    }
}

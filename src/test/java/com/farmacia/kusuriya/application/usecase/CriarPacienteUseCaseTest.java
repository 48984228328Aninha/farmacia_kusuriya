package com.farmacia.kusuriya.application.usecase;

import com.farmacia.kusuriya.domain.entity.Paciente;
import com.farmacia.kusuriya.domain.repository.PacienteRepository;
import com.farmacia.kusuriya.domain.valueobject.CPF;
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
class CriarPacienteUseCaseTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CriarPacienteUseCase criarPacienteUseCase;

    @Test
    void deveCriarPacienteComSucesso() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "01000000");
        Paciente paciente = new Paciente("João Silva", cpf, "joao@email.com", "11999999999", endereco);

        when(pacienteRepository.existsByCpf(cpf)).thenReturn(false);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente resultado = criarPacienteUseCase.executar(paciente);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void deveRejeitarPacienteDuplicado() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "01000000");
        Paciente paciente = new Paciente("João Silva", cpf, "joao@email.com", "11999999999", endereco);

        when(pacienteRepository.existsByCpf(cpf)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> criarPacienteUseCase.executar(paciente));
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }
}

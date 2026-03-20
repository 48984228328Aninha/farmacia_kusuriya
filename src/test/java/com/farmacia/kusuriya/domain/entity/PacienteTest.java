package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CPF;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void deveCriarPacienteValido() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "01000000");
        
        Paciente paciente = new Paciente("João Silva", cpf, "joao@email.com", "11999999999", endereco);
        
        assertEquals("João Silva", paciente.getNome());
        assertEquals(cpf, paciente.getCpf());
        assertEquals("joao@email.com", paciente.getEmail());
    }

    @Test
    void deveRejeitarPacienteComNomeNulo() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "01000000");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Paciente(null, cpf, "joao@email.com", "11999999999", endereco));
    }

    @Test
    void deveRejeitarEmailInvalido() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "01000000");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Paciente("João Silva", cpf, "emailinvalido", "11999999999", endereco));
    }
}

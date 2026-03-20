package com.farmacia.kusuriya.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoValido() {
        Endereco endereco = new Endereco("Rua A", "123", "Apto 1", "Centro", "São Paulo", "SP", "01000000");
        
        assertEquals("Rua A", endereco.getRua());
        assertEquals("123", endereco.getNumero());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("01000000", endereco.getCep());
    }

    @Test
    void deveRejeitarEnderecoComCamposObrigatoriosNulos() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Endereco(null, "123", null, "Centro", "São Paulo", "SP", "01000000"));
    }

    @Test
    void deveRejeitarCEPInvalido() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Endereco("Rua A", "123", null, "Centro", "São Paulo", "SP", "123"));
    }
}

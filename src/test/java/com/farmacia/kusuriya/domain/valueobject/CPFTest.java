package com.farmacia.kusuriya.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    void deveCriarCPFValido() {
        CPF cpf = new CPF("12345678901");
        assertEquals("12345678901", cpf.getValor());
    }

    @Test
    void deveRejeitarCPFNulo() {
        assertThrows(IllegalArgumentException.class, () -> new CPF(null));
    }

    @Test
    void deveRejeitarCPFVazio() {
        assertThrows(IllegalArgumentException.class, () -> new CPF(""));
    }

    @Test
    void deveRejeitarCPFComTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new CPF("123"));
    }

    @Test
    void deveCompararCPFsIguais() {
        CPF cpf1 = new CPF("12345678901");
        CPF cpf2 = new CPF("12345678901");
        assertEquals(cpf1, cpf2);
    }
}

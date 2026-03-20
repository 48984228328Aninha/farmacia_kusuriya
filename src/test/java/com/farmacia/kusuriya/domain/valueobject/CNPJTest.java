package com.farmacia.kusuriya.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CNPJTest {

    @Test
    void deveCriarCNPJValido() {
        CNPJ cnpj = new CNPJ("12345678000190");
        assertEquals("12345678000190", cnpj.getValor());
    }

    @Test
    void deveRejeitarCNPJNulo() {
        assertThrows(IllegalArgumentException.class, () -> new CNPJ(null));
    }

    @Test
    void deveRejeitarCNPJVazio() {
        assertThrows(IllegalArgumentException.class, () -> new CNPJ(""));
    }

    @Test
    void deveRejeitarCNPJComTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new CNPJ("123456"));
    }

    @Test
    void deveCompararCNPJsIguais() {
        CNPJ cnpj1 = new CNPJ("12345678000190");
        CNPJ cnpj2 = new CNPJ("12345678000190");
        assertEquals(cnpj1, cnpj2);
    }
}

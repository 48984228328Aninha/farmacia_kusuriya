package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FarmaciaTest {

    @Test
    void deveCriarFarmaciaValida() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        Farmacia farmacia = new Farmacia(
            "Farmácia Central",
            cnpj,
            endereco,
            "11999999999",
            "contato@farmacia.com.br",
            "Seg-Sex: 8h-20h"
        );
        
        assertEquals("Farmácia Central", farmacia.getNome());
        assertEquals(cnpj, farmacia.getCnpj());
        assertEquals("11999999999", farmacia.getTelefone());
        assertTrue(farmacia.isAtiva());
    }

    @Test
    void deveRejeitarFarmaciaComCNPJNulo() {
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Farmacia("Farmácia Central", null, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h"));
    }

    @Test
    void deveRejeitarFarmaciaComNomeVazio() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Farmacia("", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h"));
    }

    @Test
    void deveRejeitarFarmaciaComEnderecoNulo() {
        CNPJ cnpj = new CNPJ("12345678000190");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Farmacia("Farmácia Central", cnpj, null, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h"));
    }

    @Test
    void deveRejeitarEmailInvalido() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "emailinvalido", "Seg-Sex: 8h-20h"));
    }

    @Test
    void deveCompararFarmaciasPorCNPJ() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        Farmacia farmacia1 = new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h");
        Farmacia farmacia2 = new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h");
        
        assertEquals(farmacia1, farmacia2);
    }

    @Test
    void deveInativarFarmacia() {
        CNPJ cnpj = new CNPJ("12345678000190");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        
        Farmacia farmacia = new Farmacia("Farmácia Central", cnpj, endereco, "11999999999", "contato@farmacia.com.br", "Seg-Sex: 8h-20h");
        
        assertTrue(farmacia.isAtiva());
        farmacia.inativar();
        assertFalse(farmacia.isAtiva());
    }
}

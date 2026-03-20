package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CPF;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReceitaTestUpdated {

    @Test
    void deveCriarReceitaComObservacoes() {
        Paciente paciente = criarPaciente();
        Medico medico = criarMedico();
        
        Receita receita = new Receita(
            paciente,
            medico,
            "https://s3.amazonaws.com/receita.pdf",
            LocalDateTime.now(),
            "Tomar em jejum"
        );
        
        assertEquals("Tomar em jejum", receita.getObservacoes());
        assertFalse(receita.isValidada());
        assertNull(receita.getDataValidacao());
    }

    @Test
    void deveValidarReceitaComDataValidacao() {
        Paciente paciente = criarPaciente();
        Medico medico = criarMedico();
        
        Receita receita = new Receita(
            paciente,
            medico,
            "https://s3.amazonaws.com/receita.pdf",
            LocalDateTime.now()
        );
        
        assertFalse(receita.isValidada());
        
        receita.validar();
        
        assertTrue(receita.isValidada());
        assertNotNull(receita.getDataValidacao());
    }

    @Test
    void deveRejeitarValidacaoDuplicada() {
        Paciente paciente = criarPaciente();
        Medico medico = criarMedico();
        
        Receita receita = new Receita(
            paciente,
            medico,
            "https://s3.amazonaws.com/receita.pdf",
            LocalDateTime.now()
        );
        
        receita.validar();
        
        assertThrows(IllegalStateException.class, () -> receita.validar());
    }

    private Paciente criarPaciente() {
        CPF cpf = new CPF("12345678901");
        Endereco endereco = new Endereco("Rua A", "100", null, "Centro", "São Paulo", "SP", "01000000");
        return new Paciente("João Silva", cpf, "joao@email.com", "11999999999", endereco);
    }

    private Medico criarMedico() {
        CRM crm = new CRM("123456", "SP");
        return new Medico("Dr. Maria Santos", crm, "Cardiologia");
    }
}

package com.farmacia.kusuriya.domain.entity;

import com.farmacia.kusuriya.domain.valueobject.CRM;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void deveCriarMedicoValido() {
        CRM crm = new CRM("123456", "SP");
        
        Medico medico = new Medico("Dr. João Silva", crm, "Cardiologia");
        
        assertEquals("Dr. João Silva", medico.getNome());
        assertEquals(crm, medico.getCrm());
        assertEquals("Cardiologia", medico.getEspecialidade());
    }

    @Test
    void deveCriarMedicoComTelefoneEEmail() {
        CRM crm = new CRM("123456", "SP");
        
        Medico medico = new Medico("Dr. João Silva", crm, "Cardiologia", "11999999999", "dr.joao@email.com");
        
        assertEquals("11999999999", medico.getTelefone());
        assertEquals("dr.joao@email.com", medico.getEmail());
    }

    @Test
    void deveRejeitarMedicoComCRMNulo() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Medico("Dr. João Silva", null, "Cardiologia"));
    }

    @Test
    void deveRejeitarMedicoComNomeVazio() {
        CRM crm = new CRM("123456", "SP");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Medico("", crm, "Cardiologia"));
    }

    @Test
    void deveRejeitarMedicoComEspecialidadeVazia() {
        CRM crm = new CRM("123456", "SP");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Medico("Dr. João Silva", crm, ""));
    }

    @Test
    void deveCompararMedicosPorCRM() {
        CRM crm = new CRM("123456", "SP");
        
        Medico medico1 = new Medico("Dr. João Silva", crm, "Cardiologia");
        Medico medico2 = new Medico("Dr. João Silva", crm, "Cardiologia");
        
        assertEquals(medico1, medico2);
    }
}

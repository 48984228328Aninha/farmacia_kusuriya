package com.farmacia.kusuriya.domain.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusPedidoTest {

    @Test
    void deveConterTodosOsStatusEsperados() {
        StatusPedido[] statuses = StatusPedido.values();
        
        assertTrue(statuses.length >= 4);
        assertNotNull(StatusPedido.valueOf("CRIADO"));
        assertNotNull(StatusPedido.valueOf("CONFIRMADO"));
        assertNotNull(StatusPedido.valueOf("SEPARADO"));
        assertNotNull(StatusPedido.valueOf("CANCELADO"));
    }
}

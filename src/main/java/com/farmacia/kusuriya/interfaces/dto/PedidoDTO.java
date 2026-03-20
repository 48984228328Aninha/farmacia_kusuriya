package com.farmacia.kusuriya.interfaces.dto;

import java.util.List;

public record PedidoDTO(
    Long pacienteId,
    Long receitaId,
    List<Long> medicamentosIds
) {}

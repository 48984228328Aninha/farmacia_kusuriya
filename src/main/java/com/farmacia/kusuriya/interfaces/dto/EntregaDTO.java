package com.farmacia.kusuriya.interfaces.dto;

public record EntregaDTO(
    Long pedidoId,
    EnderecoDTO enderecoEntrega,
    String tipoEntrega
) {}

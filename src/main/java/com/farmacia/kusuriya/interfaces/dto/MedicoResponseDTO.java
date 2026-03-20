package com.farmacia.kusuriya.interfaces.dto;

public record MedicoResponseDTO(
    Long id,
    String nome,
    String crmNumero,
    String crmEstado,
    String especialidade,
    String telefone,
    String email
) {}

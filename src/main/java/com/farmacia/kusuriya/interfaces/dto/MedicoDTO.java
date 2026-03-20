package com.farmacia.kusuriya.interfaces.dto;

public record MedicoDTO(
    String nome,
    String crmNumero,
    String crmEstado,
    String especialidade,
    String telefone,
    String email
) {}

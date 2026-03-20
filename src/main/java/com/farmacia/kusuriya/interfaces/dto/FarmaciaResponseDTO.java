package com.farmacia.kusuriya.interfaces.dto;

public record FarmaciaResponseDTO(
    Long id,
    String nome,
    String cnpj,
    EnderecoDTO endereco,
    String telefone,
    String email,
    String horarioFuncionamento,
    boolean ativa
) {}

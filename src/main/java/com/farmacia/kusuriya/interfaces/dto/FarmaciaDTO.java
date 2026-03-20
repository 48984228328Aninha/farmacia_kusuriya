package com.farmacia.kusuriya.interfaces.dto;

public record FarmaciaDTO(
    String nome,
    String cnpj,
    EnderecoDTO endereco,
    String telefone,
    String email,
    String horarioFuncionamento
) {}

package com.farmacia.kusuriya.interfaces.dto;

public record PacienteDTO(
    String nome,
    String cpf,
    String email,
    String telefone,
    EnderecoDTO endereco
) {}

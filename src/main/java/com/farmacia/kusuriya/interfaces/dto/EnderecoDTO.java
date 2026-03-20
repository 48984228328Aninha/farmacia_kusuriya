package com.farmacia.kusuriya.interfaces.dto;

public record EnderecoDTO(
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado,
    String cep
) {}

package com.farmacia.kusuriya.domain.valueobject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArquivoReceita {
    
    private static final List<String> TIPOS_PERMITIDOS = Arrays.asList(
        "application/pdf",
        "image/jpeg",
        "image/png"
    );
    
    private static final long TAMANHO_MAXIMO = 5 * 1024 * 1024; // 5MB
    
    private final String nomeOriginal;
    private final String nomeArmazenado;
    private final String tipoArquivo;
    private final Long tamanho;
    private final String url;

    public ArquivoReceita(String nomeOriginal, String nomeArmazenado, String tipoArquivo, Long tamanho, String url) {
        if (nomeOriginal == null || nomeOriginal.isBlank()) {
            throw new IllegalArgumentException("Nome original não pode ser nulo ou vazio");
        }
        if (nomeArmazenado == null || nomeArmazenado.isBlank()) {
            throw new IllegalArgumentException("Nome armazenado não pode ser nulo ou vazio");
        }
        if (tipoArquivo == null || !TIPOS_PERMITIDOS.contains(tipoArquivo)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido. Apenas PDF, JPG e PNG são aceitos");
        }
        if (tamanho == null || tamanho <= 0) {
            throw new IllegalArgumentException("Tamanho do arquivo inválido");
        }
        if (tamanho > TAMANHO_MAXIMO) {
            throw new IllegalArgumentException("Arquivo excede o tamanho máximo de 5MB");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL não pode ser nula ou vazia");
        }
        
        this.nomeOriginal = nomeOriginal;
        this.nomeArmazenado = nomeArmazenado;
        this.tipoArquivo = tipoArquivo;
        this.tamanho = tamanho;
        this.url = url;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public String getNomeArmazenado() {
        return nomeArmazenado;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArquivoReceita that = (ArquivoReceita) o;
        return Objects.equals(nomeArmazenado, that.nomeArmazenado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeArmazenado);
    }
}

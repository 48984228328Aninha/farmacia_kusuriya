package com.farmacia.kusuriya.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArquivoReceitaTest {

    @Test
    void deveCriarArquivoReceitaValido() {
        ArquivoReceita arquivo = new ArquivoReceita(
            "receita.pdf",
            "uuid-123-receita.pdf",
            "application/pdf",
            1024000L,
            "https://s3.amazonaws.com/bucket/uuid-123-receita.pdf"
        );
        
        assertEquals("receita.pdf", arquivo.getNomeOriginal());
        assertEquals("uuid-123-receita.pdf", arquivo.getNomeArmazenado());
        assertEquals("application/pdf", arquivo.getTipoArquivo());
        assertEquals(1024000L, arquivo.getTamanho());
        assertEquals("https://s3.amazonaws.com/bucket/uuid-123-receita.pdf", arquivo.getUrl());
    }

    @Test
    void deveRejeitarArquivoComNomeOriginalNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> new ArquivoReceita(null, "uuid.pdf", "application/pdf", 1024L, "http://url"));
    }

    @Test
    void deveRejeitarArquivoComNomeArmazenadoNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> new ArquivoReceita("receita.pdf", null, "application/pdf", 1024L, "http://url"));
    }

    @Test
    void deveRejeitarArquivoComTipoInvalido() {
        assertThrows(IllegalArgumentException.class,
            () -> new ArquivoReceita("receita.exe", "uuid.exe", "application/exe", 1024L, "http://url"));
    }

    @Test
    void deveRejeitarArquivoComTamanhoExcedido() {
        long tamanhoMaximo = 5 * 1024 * 1024; // 5MB
        assertThrows(IllegalArgumentException.class,
            () -> new ArquivoReceita("receita.pdf", "uuid.pdf", "application/pdf", tamanhoMaximo + 1, "http://url"));
    }

    @Test
    void deveAceitarArquivoPDF() {
        assertDoesNotThrow(() -> 
            new ArquivoReceita("receita.pdf", "uuid.pdf", "application/pdf", 1024L, "http://url"));
    }

    @Test
    void deveAceitarArquivoJPG() {
        assertDoesNotThrow(() -> 
            new ArquivoReceita("receita.jpg", "uuid.jpg", "image/jpeg", 1024L, "http://url"));
    }

    @Test
    void deveAceitarArquivoPNG() {
        assertDoesNotThrow(() -> 
            new ArquivoReceita("receita.png", "uuid.png", "image/png", 1024L, "http://url"));
    }
}

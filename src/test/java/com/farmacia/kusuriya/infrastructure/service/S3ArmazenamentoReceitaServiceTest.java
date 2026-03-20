package com.farmacia.kusuriya.infrastructure.service;

import com.farmacia.kusuriya.domain.valueobject.ArquivoReceita;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3ArmazenamentoReceitaServiceTest {

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private S3ArmazenamentoReceitaService service;

    @Test
    void deveFazerUploadDeArquivoPDF() {
        byte[] arquivo = "conteudo-pdf".getBytes();
        String nomeOriginal = "receita.pdf";
        String contentType = "application/pdf";

        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
            .thenReturn(PutObjectResponse.builder().build());

        ArquivoReceita resultado = service.upload(arquivo, nomeOriginal, contentType);

        assertNotNull(resultado);
        assertEquals(nomeOriginal, resultado.getNomeOriginal());
        assertEquals(contentType, resultado.getTipoArquivo());
        assertTrue(resultado.getUrl().contains("s3"));
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    void deveFazerUploadDeArquivoJPG() {
        byte[] arquivo = "conteudo-jpg".getBytes();
        String nomeOriginal = "receita.jpg";
        String contentType = "image/jpeg";

        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
            .thenReturn(PutObjectResponse.builder().build());

        ArquivoReceita resultado = service.upload(arquivo, nomeOriginal, contentType);

        assertNotNull(resultado);
        assertEquals("image/jpeg", resultado.getTipoArquivo());
    }

    @Test
    void deveValidarArquivoPDF() {
        assertTrue(service.validarArquivo("application/pdf", 1024L));
    }

    @Test
    void deveValidarArquivoJPG() {
        assertTrue(service.validarArquivo("image/jpeg", 1024L));
    }

    @Test
    void deveValidarArquivoPNG() {
        assertTrue(service.validarArquivo("image/png", 1024L));
    }

    @Test
    void deveRejeitarArquivoComTipoInvalido() {
        assertFalse(service.validarArquivo("application/exe", 1024L));
    }

    @Test
    void deveRejeitarArquivoMuitoGrande() {
        long tamanhoMaximo = 5 * 1024 * 1024; // 5MB
        assertFalse(service.validarArquivo("application/pdf", tamanhoMaximo + 1));
    }

    @Test
    void deveGerarNomeUnicoParaArquivo() {
        byte[] arquivo = "conteudo".getBytes();
        
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
            .thenReturn(PutObjectResponse.builder().build());

        ArquivoReceita resultado1 = service.upload(arquivo, "receita.pdf", "application/pdf");
        ArquivoReceita resultado2 = service.upload(arquivo, "receita.pdf", "application/pdf");

        assertNotEquals(resultado1.getNomeArmazenado(), resultado2.getNomeArmazenado());
    }
}

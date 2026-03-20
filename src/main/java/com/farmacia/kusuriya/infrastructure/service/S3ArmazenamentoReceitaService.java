package com.farmacia.kusuriya.infrastructure.service;

import com.farmacia.kusuriya.domain.service.ArmazenamentoReceitaService;
import com.farmacia.kusuriya.domain.valueobject.ArquivoReceita;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class S3ArmazenamentoReceitaService implements ArmazenamentoReceitaService {
    
    private static final List<String> TIPOS_PERMITIDOS = Arrays.asList(
        "application/pdf",
        "image/jpeg",
        "image/png"
    );
    
    private static final long TAMANHO_MAXIMO = 5 * 1024 * 1024; // 5MB
    
    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    public S3ArmazenamentoReceitaService(
            S3Client s3Client,
            @Value("${aws.s3.bucket-name}") String bucketName,
            @Value("${aws.s3.region}") String region) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.region = region;
    }

    @Override
    public ArquivoReceita upload(byte[] arquivo, String nomeOriginal, String contentType) {
        if (!validarArquivo(contentType, arquivo.length)) {
            throw new IllegalArgumentException("Arquivo inválido");
        }
        
        String nomeArmazenado = gerarNomeUnico(nomeOriginal);
        
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(nomeArmazenado)
            .contentType(contentType)
            .build();
        
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(arquivo));
        
        String url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, nomeArmazenado);
        
        return new ArquivoReceita(nomeOriginal, nomeArmazenado, contentType, (long) arquivo.length, url);
    }

    @Override
    public byte[] download(String url) {
        String key = extrairKeyDaUrl(url);
        
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
        
        return s3Client.getObjectAsBytes(getObjectRequest).asByteArray();
    }

    @Override
    public void delete(String url) {
        String key = extrairKeyDaUrl(url);
        
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
        
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public boolean validarArquivo(String contentType, long tamanho) {
        return TIPOS_PERMITIDOS.contains(contentType) && tamanho <= TAMANHO_MAXIMO;
    }
    
    private String gerarNomeUnico(String nomeOriginal) {
        String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
        return UUID.randomUUID().toString() + extensao;
    }
    
    private String extrairKeyDaUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

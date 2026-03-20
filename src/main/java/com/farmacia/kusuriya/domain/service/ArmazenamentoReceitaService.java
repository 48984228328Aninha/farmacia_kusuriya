package com.farmacia.kusuriya.domain.service;

import com.farmacia.kusuriya.domain.valueobject.ArquivoReceita;

public interface ArmazenamentoReceitaService {
    
    ArquivoReceita upload(byte[] arquivo, String nomeOriginal, String contentType);
    
    byte[] download(String url);
    
    void delete(String url);
    
    boolean validarArquivo(String contentType, long tamanho);
}

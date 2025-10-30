package com.estagio.cursosLivres.dto.arquivo;

import com.estagio.cursosLivres.entities.Arquivo;
import com.estagio.cursosLivres.entities.utils.TipoArquivo;

public record NovoArquivoResponseDTO(Long id, String titulo, TipoArquivo tipoArquivo, String message) {

    public NovoArquivoResponseDTO(Arquivo entity) {
        this (
        entity.getId(),
        entity.getTitulo(),
        entity.getTipoArquivo(),
        "Arquivo criado com sucesso"
        );
    }
}

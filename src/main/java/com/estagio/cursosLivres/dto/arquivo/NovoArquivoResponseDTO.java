package com.estagio.cursosLivres.dto.arquivo;

import com.estagio.cursosLivres.entities.Arquivo;
import com.estagio.cursosLivres.entities.utils.TipoArquivo;

public class NovoArquivoResponseDTO {

    private Long id;
    private String titulo;
    private TipoArquivo tipoArquivo;
    private String message;

    public NovoArquivoResponseDTO() {
    }

    public NovoArquivoResponseDTO(Long id, String titulo, TipoArquivo tipoArquivo, String message) {
        this.id = id;
        this.titulo = titulo;
        this.tipoArquivo = tipoArquivo;
        this.message = message;
    }

    public NovoArquivoResponseDTO(Arquivo entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        tipoArquivo = entity.getTipoArquivo();
        message = "Arquivo criado com sucesso";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

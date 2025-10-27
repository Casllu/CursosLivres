package com.estagio.cursosLivres.dto.arquivo;

import com.estagio.cursosLivres.entities.Arquivo;
import com.estagio.cursosLivres.entities.utils.TipoArquivo;

import java.time.LocalDateTime;

public class NovoArquivoRequestDTO {

    private String descricao;
    private Long cursoId;
    private TipoArquivo tipo;

    public NovoArquivoRequestDTO() {}

    public NovoArquivoRequestDTO(String descricao, Long cursoId, TipoArquivo tipo) {
        this.descricao = descricao;
        this.cursoId = cursoId;
        this.tipo = tipo;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public TipoArquivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArquivo tipo) {
        this.tipo = tipo;
    }
}

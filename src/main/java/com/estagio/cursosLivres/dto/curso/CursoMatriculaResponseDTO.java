package com.estagio.cursosLivres.dto.curso;

import com.estagio.cursosLivres.entities.Curso;

import java.math.BigDecimal;

public class CursoMatriculaResponseDTO {

    private Long cursoId;
    private String nomeCurso;
    private BigDecimal preco;

    public CursoMatriculaResponseDTO() {
    }

    public CursoMatriculaResponseDTO(Curso entity) {
        cursoId = entity.getId();
        nomeCurso = entity.getNome();
        preco = entity.getPreco();
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

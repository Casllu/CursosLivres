package com.estagio.cursosLivres.dto.matricula;

import jakarta.validation.constraints.NotNull;

public class NovaMatriculaRequestDTO {

    @NotNull
    private Long alunoId;
    @NotNull
    private Long cursoId;

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}

package com.estagio.cursosLivres.dto.matricula;

import com.estagio.cursosLivres.dto.curso.CursoDTO;
import com.estagio.cursosLivres.dto.pagamento.PagamentoDTO;
import com.estagio.cursosLivres.dto.user.UserMinDTO;
import com.estagio.cursosLivres.entities.Matricula;
import com.estagio.cursosLivres.entities.utils.MatriculaStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MatriculaDTO {

    private Long id;
    @NotNull
    private UserMinDTO aluno;
    @NotNull
    private CursoDTO curso;
    @NotNull
    private LocalDateTime dataMatricula;
    private MatriculaStatus status;
    private PagamentoDTO pagamento;

    public MatriculaDTO() {}

    public MatriculaDTO(Long id, UserMinDTO aluno, CursoDTO curso, LocalDateTime dataMatricula, MatriculaStatus status) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

    public MatriculaDTO(Matricula entity) {
        id = entity.getId();
        aluno = new UserMinDTO(entity.getAluno());
        curso = new CursoDTO(entity.getCurso());
        dataMatricula = entity.getDataMatricula();
        status = entity.getStatus();
        pagamento = entity.getPagamento() != null ? new PagamentoDTO(entity.getPagamento()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserMinDTO getAluno() {
        return aluno;
    }

    public void setAluno(UserMinDTO aluno) {
        this.aluno = aluno;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public MatriculaStatus getStatus() {
        return status;
    }

    public void setStatus(MatriculaStatus status) {
        this.status = status;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }
}

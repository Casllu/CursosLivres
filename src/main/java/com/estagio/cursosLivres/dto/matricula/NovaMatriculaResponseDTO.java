package com.estagio.cursosLivres.dto.matricula;

import com.estagio.cursosLivres.dto.curso.CursoMatriculaResponseDTO;
import com.estagio.cursosLivres.dto.pagamento.PagamentoMatriculaResponseDTO;
import com.estagio.cursosLivres.dto.user.UserMinDTO;
import com.estagio.cursosLivres.entities.Matricula;
import com.estagio.cursosLivres.entities.utils.MatriculaStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NovaMatriculaResponseDTO {

    private Long matriculaId;
    @NotNull
    private UserMinDTO aluno;
    @NotNull
    private CursoMatriculaResponseDTO curso;
    @NotNull
    private LocalDateTime dataMatricula;
    private MatriculaStatus status;
    private PagamentoMatriculaResponseDTO pagamento;

    public NovaMatriculaResponseDTO() {
    }

    public NovaMatriculaResponseDTO(Matricula entity) {
        matriculaId = entity.getId();
        aluno = new UserMinDTO(entity.getAluno());
        curso = new CursoMatriculaResponseDTO(entity.getCurso());
        dataMatricula = entity.getDataMatricula();
        status = entity.getStatus();
        pagamento = new PagamentoMatriculaResponseDTO(entity.getPagamento());
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public UserMinDTO getAluno() {
        return aluno;
    }

    public void setAluno(UserMinDTO aluno) {
        this.aluno = aluno;
    }

    public CursoMatriculaResponseDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoMatriculaResponseDTO curso) {
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

    public PagamentoMatriculaResponseDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoMatriculaResponseDTO pagamento) {
        this.pagamento = pagamento;
    }
}

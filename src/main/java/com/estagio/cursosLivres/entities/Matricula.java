package com.estagio.cursosLivres.entities;

import com.estagio.cursosLivres.entities.utils.MatriculaStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "tb_matricula",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_aluno_curso",
                columnNames = {
                        "aluno_id", "curso_id"
                }

        )
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private User aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private LocalDateTime dataMatricula;
    private MatriculaStatus status;

    @OneToOne(mappedBy = "matricula", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    public Matricula() {}

    public Matricula(Long id, User aluno, Curso curso, LocalDateTime dataMatricula, MatriculaStatus status) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAluno() {
        return aluno;
    }

    public void setAluno(User aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
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

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}

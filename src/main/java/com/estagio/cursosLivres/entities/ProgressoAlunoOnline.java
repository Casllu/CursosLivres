package com.estagio.cursosLivres.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_progresso_aluno_online")
public class ProgressoAlunoOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double percentual;

    private LocalDateTime ultimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    public ProgressoAlunoOnline() {
    }

    public ProgressoAlunoOnline(Long id, Double percentual, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.percentual = percentual;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}

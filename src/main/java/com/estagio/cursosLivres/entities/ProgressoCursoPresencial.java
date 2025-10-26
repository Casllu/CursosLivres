package com.estagio.cursosLivres.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_progresso_curso_presencial")
public class ProgressoCursoPresencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double percentual;

    private LocalDateTime ultimaAtualizacao;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private CursoPresencial cursoPresencial;

    public ProgressoCursoPresencial(Long id, Double percentual, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.percentual = percentual;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public ProgressoCursoPresencial() {}

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

    public CursoPresencial getCursoPresencial() {
        return cursoPresencial;
    }

    public void setCursoPresencial(CursoPresencial cursoPresencial) {
        this.cursoPresencial = cursoPresencial;
    }
}

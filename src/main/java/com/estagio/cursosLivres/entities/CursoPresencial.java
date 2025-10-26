package com.estagio.cursosLivres.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_curso_presencial")
@PrimaryKeyJoinColumn(name = "id")
public class CursoPresencial extends Curso{

    private String local;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer minAlunos;
    private Integer maxAlunos;
    @OneToOne(mappedBy = "cursoPresencial", cascade = CascadeType.ALL)
    private ProgressoCursoPresencial progressoCursoPresencial ;

    public CursoPresencial() {}

    public CursoPresencial(String local, LocalDateTime dataInicio, LocalDateTime dataFim, Integer minAlunos, Integer maxAlunos) {
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.minAlunos = minAlunos;
        this.maxAlunos = maxAlunos;
    }

    public CursoPresencial(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel, String local, LocalDateTime dataInicio, LocalDateTime dataFim, Integer minAlunos, Integer maxAlunos) {
        super(id, nome, descricao, cargaHoraria, preco, certificadoDisponivel);
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.minAlunos = minAlunos;
        this.maxAlunos = maxAlunos;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getMinAlunos() {
        return minAlunos;
    }

    public void setMinAlunos(Integer minAlunos) {
        this.minAlunos = minAlunos;
    }

    public Integer getMaxAlunos() {
        return maxAlunos;
    }

    public void setMaxAlunos(Integer maxAlunos) {
        this.maxAlunos = maxAlunos;
    }
}

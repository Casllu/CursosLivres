package com.estagio.cursosLivres.dto.curso;

import com.estagio.cursosLivres.dto.user.UserMinDTO;
import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.entities.CursoPresencial;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CursoPresencialDTO extends CursoDTO{

    @NotBlank
    private String local;
    @Future
    private LocalDateTime dataInicio;
    @Future
    private LocalDateTime dataFim;
    @NotNull
    private Integer minAlunos;
    @NotNull
    private Integer maxAlunos;

    public CursoPresencialDTO() {}

    public CursoPresencialDTO(String local, LocalDateTime dataInicio, LocalDateTime dataFim, Integer minAlunos, Integer maxAlunos) {
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.minAlunos = minAlunos;
        this.maxAlunos = maxAlunos;
    }

    public CursoPresencialDTO(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel, UserMinDTO professor, String local, LocalDateTime dataInicio, LocalDateTime dataFim, Integer minAlunos, Integer maxAlunos) {
        super(id, nome, descricao, cargaHoraria, preco, certificadoDisponivel, professor);
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.minAlunos = minAlunos;
        this.maxAlunos = maxAlunos;
    }

    public CursoPresencialDTO(CursoPresencial entity) {
        super(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getCargaHoraria(), entity.getPreco(), entity.isCertificadoDisponivel(), new UserMinDTO(entity.getProfessor()));
        local = entity.getLocal();
        dataInicio = entity.getDataInicio();
        dataFim = entity.getDataFim();
        minAlunos = entity.getMinAlunos();
        maxAlunos = entity.getMaxAlunos();
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

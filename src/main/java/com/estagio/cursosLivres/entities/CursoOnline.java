package com.estagio.cursosLivres.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_curso_online")
@PrimaryKeyJoinColumn(name = "id")
public class CursoOnline extends Curso {

    private Integer validadeDias;
    private Integer qtdAulas;
    private Integer qtdCapitulos;

    public CursoOnline() {}

    public CursoOnline(Integer validadeDias, Integer qtdAulas, Integer qtdCapitulos) {
        this.validadeDias = validadeDias;
        this.qtdAulas = qtdAulas;
        this.qtdCapitulos = qtdCapitulos;
    }

    public CursoOnline(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel, Integer validadeDias, Integer qtdAulas, Integer qtdCapitulos) {
        super(id, nome, descricao, cargaHoraria, preco, certificadoDisponivel);
        this.validadeDias = validadeDias;
        this.qtdAulas = qtdAulas;
        this.qtdCapitulos = qtdCapitulos;
    }

    public Integer getValidadeDias() {
        return validadeDias;
    }

    public void setValidadeDias(Integer validadeDias) {
        this.validadeDias = validadeDias;
    }

    public Integer getQtdAulas() {
        return qtdAulas;
    }

    public void setQtdAulas(Integer qtdAulas) {
        this.qtdAulas = qtdAulas;
    }

    public Integer getQtdCapitulos() {
        return qtdCapitulos;
    }

    public void setQtdCapitulos(Integer qtdCapitulos) {
        this.qtdCapitulos = qtdCapitulos;
    }
}

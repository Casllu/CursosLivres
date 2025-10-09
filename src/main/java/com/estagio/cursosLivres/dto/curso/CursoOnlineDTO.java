package com.estagio.cursosLivres.dto.curso;

import com.estagio.cursosLivres.dto.user.UserDTO;
import com.estagio.cursosLivres.dto.user.UserMinDTO;
import com.estagio.cursosLivres.entities.CursoOnline;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CursoOnlineDTO extends CursoDTO{

    @NotNull
    private Integer validadeDias;

    @NotNull
    private Integer qtdAulas;

    @NotNull
    private Integer qtdCapitulos;

    public CursoOnlineDTO() {}

    public CursoOnlineDTO(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel, Integer validadeDias, Integer qtdAulas, Integer qtdCapitulos, UserMinDTO professor) {
        super(id, nome, descricao, cargaHoraria, preco, certificadoDisponivel, professor);
        this.validadeDias = validadeDias;
        this.qtdAulas = qtdAulas;
        this.qtdCapitulos = qtdCapitulos;
    }

    public CursoOnlineDTO(CursoOnline entity) {
        super(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getCargaHoraria(), entity.getPreco(), entity.isCertificadoDisponivel(), new UserMinDTO(entity.getProfessor()));
        validadeDias = entity.getValidadeDias();
        qtdAulas = entity.getQtdAulas();
        qtdCapitulos = entity.getQtdCapitulos();
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

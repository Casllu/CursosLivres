package com.estagio.cursosLivres.dto.curso;

import com.estagio.cursosLivres.dto.user.UserMinDTO;
import com.estagio.cursosLivres.entities.Curso;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CursoDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotNull(message = "Campo obrigatório")
    private Integer cargaHoraria;

    @NotNull(message = "Campo obrigatório")
    @Digits(integer=6, fraction=2)
    private BigDecimal preco;

    @NotNull(message = "Campo obrigatório")
    private boolean certificadoDisponivel;

    @NotNull(message = "Professor é obrigatório")
    private UserMinDTO professor;

    public CursoDTO() {}

    public CursoDTO(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel,  UserMinDTO professor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.preco = preco;
        this.certificadoDisponivel = certificadoDisponivel;
        this.professor = professor;
    }

    public CursoDTO(Curso entity) {
        id = entity.getId();
        nome = entity.getNome();
        descricao = entity.getDescricao();
        cargaHoraria = entity.getCargaHoraria();
        preco = entity.getPreco();
        certificadoDisponivel = entity.isCertificadoDisponivel();
        professor = new UserMinDTO(entity.getProfessor());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isCertificadoDisponivel() {
        return certificadoDisponivel;
    }

    public void setCertificadoDisponivel(boolean certificadoDisponivel) {
        this.certificadoDisponivel = certificadoDisponivel;
    }

    public UserMinDTO getProfessor() {
        return professor;
    }

    public void setProfessor(UserMinDTO professor) {
        this.professor = professor;
    }
}

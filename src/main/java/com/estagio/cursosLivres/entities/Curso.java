package com.estagio.cursosLivres.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_curso")
@Inheritance(strategy = InheritanceType.JOINED)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao    ;
    private Integer cargaHoraria;
    private BigDecimal preco;
    private boolean certificadoDisponivel;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    @ManyToMany
    @JoinTable(
            name = "tb_curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<User> alunos = new HashSet<>();

    public Curso() {
    }

    public Curso(Long id, String nome, String descricao, Integer cargaHoraria, BigDecimal preco, boolean certificadoDisponivel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.preco = preco;
        this.certificadoDisponivel = certificadoDisponivel;
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

    public Set<User> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<User> alunos) {
        this.alunos = alunos;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }
}

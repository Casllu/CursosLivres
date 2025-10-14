package com.estagio.cursosLivres.entities;

import com.estagio.cursosLivres.entities.utils.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private PagamentoStatus status;
    private BigDecimal preco;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "matricula_id", unique = true)
    })
    private Matricula matricula;

    public Pagamento() {
    }

    public Pagamento(Long id, Instant moment, PagamentoStatus status, BigDecimal preco) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public PagamentoStatus getStatus() {
        return status;
    }

    public void setStatus(PagamentoStatus status) {
        this.status = status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(moment, pagamento.moment) && status == pagamento.status && Objects.equals(matricula, pagamento.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moment, status, matricula);
    }
}

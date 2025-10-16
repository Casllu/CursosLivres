package com.estagio.cursosLivres.dto.pagamento;

import jakarta.validation.constraints.NotNull;

public class PagamentoCartaoDTO {

    @NotNull
    private Long matriculaId;

    @NotNull
    private String token;

    @NotNull
    private String metodoPagamentoId;

    @NotNull
    private Integer qtdParcelas;

    @NotNull
    private PagadorDTO pagador;

    public PagamentoCartaoDTO() {
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMetodoPagamentoId() {
        return metodoPagamentoId;
    }

    public void setMetodoPagamentoId(String metodoPagamentoId) {
        this.metodoPagamentoId = metodoPagamentoId;
    }

    public Integer getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public PagadorDTO getPagador() {
        return pagador;
    }

    public void setPagador(PagadorDTO pagador) {
        this.pagador = pagador;
    }
}

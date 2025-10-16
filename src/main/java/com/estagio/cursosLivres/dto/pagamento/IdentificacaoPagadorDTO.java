package com.estagio.cursosLivres.dto.pagamento;

import jakarta.validation.constraints.NotNull;

public class IdentificacaoPagadorDTO {

    @NotNull
    private String tipo;

    @NotNull
    private String numero;

    public IdentificacaoPagadorDTO() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}

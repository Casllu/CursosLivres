package com.estagio.cursosLivres.dto.pagamento;

import jakarta.validation.constraints.NotNull;

public class PagadorDTO {

    @NotNull
    private String email;

    @NotNull
    private IdentificacaoPagadorDTO identificador;

    public PagadorDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IdentificacaoPagadorDTO getIdentificador() {
        return identificador;
    }

    public void setIdentificador(IdentificacaoPagadorDTO identificador) {
        this.identificador = identificador;
    }
}

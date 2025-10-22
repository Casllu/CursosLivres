package com.estagio.cursosLivres.dto.pagamento;

public class PagamentoResponseDTO {

    private Long id;
    private String status;
    private String detalhe;
    private String message;

    public PagamentoResponseDTO(Long id, String status, String detalhe, String message) {
        this.id = id;
        this.status = status;
        this.detalhe = detalhe;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

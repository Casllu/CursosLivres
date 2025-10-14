package com.estagio.cursosLivres.dto.pagamento;

import com.estagio.cursosLivres.dto.matricula.MatriculaDTO;
import com.estagio.cursosLivres.entities.Pagamento;
import com.estagio.cursosLivres.entities.utils.PagamentoStatus;

import java.time.Instant;

public class PagamentoDTO {

    private Long id;

    private Instant moment;

    private PagamentoStatus status;

    public PagamentoDTO() {}

    public PagamentoDTO(Long id, Instant moment, PagamentoStatus status) {
        this.id = id;
        this.moment = moment;
        this.status = status;
    }

    public PagamentoDTO(Pagamento entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
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
}

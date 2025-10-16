package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.pagamento.PagamentoCartaoDTO;
import com.estagio.cursosLivres.dto.pagamento.PagamentoResponseDTO;
import com.estagio.cursosLivres.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALUNO')")
    public ResponseEntity<PagamentoResponseDTO> efetuarPagamento(@RequestBody PagamentoCartaoDTO pagamentoCartaoDTO) {
        PagamentoResponseDTO dto = pagamentoService.processarPagamento(pagamentoCartaoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}

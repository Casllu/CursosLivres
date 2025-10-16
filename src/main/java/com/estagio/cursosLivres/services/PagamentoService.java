package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.pagamento.PagamentoCartaoDTO;
import com.estagio.cursosLivres.dto.pagamento.PagamentoResponseDTO;
import com.estagio.cursosLivres.entities.Matricula;
import com.estagio.cursosLivres.entities.Pagamento;
import com.estagio.cursosLivres.entities.utils.MatriculaStatus;
import com.estagio.cursosLivres.entities.utils.PagamentoStatus;
import com.estagio.cursosLivres.repositories.MatriculaRepository;
import com.estagio.cursosLivres.repositories.PagamentoRepository;
import com.estagio.cursosLivres.services.exceptions.MercadoPagoException;
import com.estagio.cursosLivres.services.exceptions.ResourceNotFoundException;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Transactional
    public PagamentoResponseDTO processarPagamento(PagamentoCartaoDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.getMatriculaId())
                .orElseThrow(() -> new ResourceNotFoundException("Matricula inexistente"));

        try {

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(matricula.getCurso().getPreco())
                            .token(dto.getToken())
                            .description(matricula.getCurso().getNome())
                            .installments(dto.getQtdParcelas())
                            .paymentMethodId(dto.getMetodoPagamentoId())
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(dto.getPagador().getEmail())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(dto.getPagador().getIdentificador().getTipo())
                                                            .number(dto.getPagador().getIdentificador().getNumero())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            atualizarDadosMatriculaPagamento(dto.getMatriculaId(), createdPayment.getId(), createdPayment.getStatus());

            return new PagamentoResponseDTO(
                    createdPayment.getId(),
                    createdPayment.getStatus(),
                    createdPayment.getStatusDetail()
            );

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }

    @Transactional
    public void atualizarDadosMatriculaPagamento(Long matriculaId, Long mercadoPagoId, String statusMercadoPago) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matricula inexistente"));

        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setStatus(MatriculaStatus.ATIVA);
        matriculaRepository.save(matricula);

        Pagamento pagamento = pagamentoRepository.findByMatriculaId(matriculaId);

        pagamento.setMoment(Instant.now());
        pagamento.setStatus(PagamentoStatus.CONFIRMADO);
        pagamento.setMercadoPagoId(mercadoPagoId);
        pagamento.setStatusMercadoPago(statusMercadoPago);
        pagamentoRepository.save(pagamento);
    }
}

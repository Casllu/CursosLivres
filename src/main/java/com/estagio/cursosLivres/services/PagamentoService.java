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
    private MatriculaService matriculaService;

    @Transactional(readOnly = true)
    public Pagamento buscarPagamento(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento inexistente"));
    }

    @Transactional
    public PagamentoResponseDTO processarPagamento(PagamentoCartaoDTO dto) {
        Matricula matricula = matriculaService.buscarMatricula(dto.getMatriculaId());

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

            atualizarDadosPagamento(matricula.getPagamento(), createdPayment);
            String mensagemUsuario = montarMensagemParaUsuario(createdPayment);

            return new PagamentoResponseDTO(
                    createdPayment.getId(),
                    createdPayment.getStatus(),
                    createdPayment.getStatusDetail(),
                    mensagemUsuario
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
    public void atualizarDadosPagamento(Pagamento pagamento,  Payment payment) {
        String status = payment.getStatus();

        switch (status) {
            case "approved":
                pagamento.setStatus(PagamentoStatus.CONFIRMADO);
                pagamento.setMoment(Instant.now());
                pagamento.setMercadoPagoId(payment.getId());

                matriculaService.atualizarDadosMatricula(pagamento.getMatricula(), MatriculaStatus.ATIVA);
                break;
            case "pending":
            case "in_process":
                pagamento.setStatus(PagamentoStatus.PENDENTE);
                // informar usuário, aguardar confirmação/futuro webhook
                break;
            case "rejected":
                pagamento.setStatus(PagamentoStatus.REJEITADO);
                // informar usuário, permitir tentar novamente
                break;
            case "cancelled":
                pagamento.setStatus(PagamentoStatus.CANCELADO);
                break;
            case "refunded":
                pagamento.setStatus(PagamentoStatus.ESTORNADO);
                // suspender acesso se necessário
                break;
            case "charged_back":
            case "in_mediation":
                pagamento.setStatus(PagamentoStatus.LITIGIO);
                // pausar acesso, informar equipe de suporte
                break;
            default:
                // fallback seguro (pode logar para análise)
                pagamento.setStatus(PagamentoStatus.AGUARDANDO);
        }

        pagamentoRepository.save(pagamento);
    }

    private String montarMensagemParaUsuario(Payment payment) {
        switch (payment.getStatus()) {
            case "approved":
                return "Pagamento aprovado! Seu acesso foi liberado.";
            case "in_process":
            case "pending":
                return "Seu pagamento está sendo processado. Assim que for confirmado, você receberá um e-mail.";
            case "rejected":
                return "Pagamento recusado. Verifique os dados do cartão ou tente outro método.";
            case "cancelled":
                return "O pagamento foi cancelado.";
            case "refunded":
                return "O pagamento foi estornado. Seu acesso foi suspenso até confirmarmos o reembolso.";
            default:
                return "O pagamento está em análise. Aguarde atualização.";
        }
    }
}

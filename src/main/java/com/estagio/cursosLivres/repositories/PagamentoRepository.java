package com.estagio.cursosLivres.repositories;

import com.estagio.cursosLivres.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}

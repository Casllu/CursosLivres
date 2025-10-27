package com.estagio.cursosLivres.repositories;

import com.estagio.cursosLivres.entities.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    boolean existsByTitulo(String nomeArquivo);
}

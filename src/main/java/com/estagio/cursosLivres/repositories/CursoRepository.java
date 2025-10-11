package com.estagio.cursosLivres.repositories;

import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.entities.CursoOnline;
import com.estagio.cursosLivres.entities.CursoPresencial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select o from CursoOnline o")
    Page<CursoOnline> findAllCursoOnline(Pageable pageable);

    @Query("select o from CursoPresencial o")
    Page<CursoPresencial> findAllCursoPresencial(Pageable pageable);

    @Query("select o from CursoOnline o where o.id = :id")
    Optional<CursoOnline> findCursoOnlineById(@Param("id") Long id);

    @Query("select o from CursoPresencial o where o.id = :id")
    Optional<CursoPresencial> findCursoPresenciaById(@Param("id") Long id);
}

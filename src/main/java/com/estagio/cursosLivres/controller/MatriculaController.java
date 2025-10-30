package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.matricula.MatriculaResponseDTO;
import com.estagio.cursosLivres.dto.matricula.NovaMatriculaRequestDTO;
import com.estagio.cursosLivres.dto.matricula.NovaMatriculaResponseDTO;
import com.estagio.cursosLivres.services.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<Page<MatriculaResponseDTO>> findAll(Pageable pageable) {
        Page<MatriculaResponseDTO> matriculas = matriculaService.findAll(pageable);

        return ResponseEntity.ok().body(matriculas);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALUNO')")
    public ResponseEntity<MatriculaResponseDTO> findById(@PathVariable Long id){
        MatriculaResponseDTO matricula = matriculaService.findById(id);
        return ResponseEntity.ok().body(matricula);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALUNO')")
    public ResponseEntity<NovaMatriculaResponseDTO> novaMatricula(@Valid @RequestBody NovaMatriculaRequestDTO dto) {
        NovaMatriculaResponseDTO matriculaDTO = matriculaService.novaMatricula(dto.getAlunoId(), dto.getCursoId());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(matriculaDTO.getMatriculaId()).toUri();

        return ResponseEntity.created(uri).body(matriculaDTO);
    }
}

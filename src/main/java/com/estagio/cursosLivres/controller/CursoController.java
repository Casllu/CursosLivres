package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.curso.CursoDTO;
import com.estagio.cursosLivres.dto.curso.CursoOnlineDTO;
import com.estagio.cursosLivres.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<Page<CursoDTO>> findAllPaged(Pageable pageableRequest) {
        Page<CursoDTO> list = cursoService.findAllPaged(pageableRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        CursoDTO dto = cursoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/online")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CursoOnlineDTO> insertCursoOnline(@RequestBody CursoOnlineDTO cursoDTO) {
        CursoOnlineDTO newDto = cursoService.insertCursoOnline(cursoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }
}

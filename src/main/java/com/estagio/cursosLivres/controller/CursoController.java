package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.curso.CursoDTO;
import com.estagio.cursosLivres.dto.curso.CursoOnlineDTO;
import com.estagio.cursosLivres.dto.curso.CursoPresencialDTO;
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

    @GetMapping(value = "/online")
    public ResponseEntity<Page<CursoOnlineDTO>> findAllOnlinePaged(Pageable pageableRequest) {
        Page<CursoOnlineDTO> list = cursoService.findAllOnlinePaged(pageableRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/presencial")
    public ResponseEntity<Page<CursoPresencialDTO>> findAllPresencialPaged(Pageable pageableRequest) {
        Page<CursoPresencialDTO> list = cursoService.findAllPresencialPaged(pageableRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        CursoDTO dto = cursoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/online/{id}")
    public ResponseEntity<CursoOnlineDTO> findCursoOnlineById(@PathVariable Long id) {
        CursoOnlineDTO dto = cursoService.findCursoOnlineById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/presencial/{id}")
    public ResponseEntity<CursoPresencialDTO> findCursoPresencialById(@PathVariable Long id) {
        CursoPresencialDTO dto = cursoService.findCursoPresencialById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/online")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CursoOnlineDTO> insertCursoOnline(@RequestBody CursoOnlineDTO dto) {
        CursoOnlineDTO newDto = cursoService.insertCursoOnline(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PostMapping(value = "/presencial")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CursoPresencialDTO> insertCursoPresencial(@RequestBody CursoPresencialDTO dto) {
        CursoPresencialDTO newDto = cursoService.insertCursoPresencial(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/online/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CursoOnlineDTO> updateCursoOnline(@PathVariable Long id, @RequestBody CursoOnlineDTO dto) {
        CursoOnlineDTO newDto = cursoService.updateCursoOnline(id, dto);

        return ResponseEntity.ok().body(newDto);
    }

    @PutMapping(value = "/presencial/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CursoPresencialDTO> updateCursoPresencial(@PathVariable Long id, @RequestBody CursoPresencialDTO dto) {
        CursoPresencialDTO newDto = cursoService.updateCursoPresencial(id, dto);

        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.arquivo.NovoArquivoRequestDTO;
import com.estagio.cursosLivres.dto.arquivo.NovoArquivoResponseDTO;
import com.estagio.cursosLivres.entities.utils.TipoArquivo;
import com.estagio.cursosLivres.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<NovoArquivoResponseDTO> uploadArquivo(@RequestPart("file") MultipartFile file,
                                                                @RequestPart("dados")NovoArquivoRequestDTO novoArquivoRequestDTO) throws IOException {

        NovoArquivoResponseDTO dto = arquivoService.uploadVideo(file, novoArquivoRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{cursoId}/{videoFile}/url")
    public ResponseEntity<?> getSignedVideoUrl(@PathVariable Long matriculaId,
                                               @PathVariable String titulo) {

        String signedUrl = arquivoService.gerarSignedUrl(matriculaId, titulo);

        return ResponseEntity.ok().body(Map.of("signedUrl", signedUrl));
    }
}

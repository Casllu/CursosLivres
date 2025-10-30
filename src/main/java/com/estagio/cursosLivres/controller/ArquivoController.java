package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.arquivo.NovoArquivoRequestDTO;
import com.estagio.cursosLivres.dto.arquivo.NovoArquivoResponseDTO;
import com.estagio.cursosLivres.dto.arquivo.RequestSignedArquivoDTO;
import com.estagio.cursosLivres.dto.arquivo.ResponseSignedArquivoDTO;
import com.estagio.cursosLivres.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
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
                .path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/url")
    public ResponseEntity<ResponseSignedArquivoDTO> getSignedVideoUrl(@RequestBody RequestSignedArquivoDTO dto) {

        ResponseSignedArquivoDTO newDto = arquivoService.gerarSignedUrl(dto);

        return ResponseEntity.ok().body(newDto);
    }
}

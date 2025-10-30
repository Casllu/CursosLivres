package com.estagio.cursosLivres.dto.arquivo;

import com.estagio.cursosLivres.entities.Arquivo;
import com.estagio.cursosLivres.entities.utils.TipoArquivo;

import java.time.LocalDateTime;

public record NovoArquivoRequestDTO(String descricao,
                                    Long cursoId,
                                    TipoArquivo tipo) {}

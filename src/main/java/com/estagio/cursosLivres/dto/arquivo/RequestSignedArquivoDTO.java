package com.estagio.cursosLivres.dto.arquivo;

import com.estagio.cursosLivres.entities.utils.TipoArquivo;

public record RequestSignedArquivoDTO(Long cursoId,
                                      String tituloArquivo,
                                      TipoArquivo tipoArquivo
                                     )
{}
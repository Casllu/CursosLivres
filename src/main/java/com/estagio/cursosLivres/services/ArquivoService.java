package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.arquivo.NovoArquivoRequestDTO;
import com.estagio.cursosLivres.dto.arquivo.NovoArquivoResponseDTO;
import com.estagio.cursosLivres.dto.arquivo.RequestSignedArquivoDTO;
import com.estagio.cursosLivres.dto.arquivo.ResponseSignedArquivoDTO;
import com.estagio.cursosLivres.entities.Arquivo;
import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.repositories.ArquivoRepository;
import com.estagio.cursosLivres.services.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private CursoService cursoService;


    public NovoArquivoResponseDTO uploadVideo(MultipartFile file, NovoArquivoRequestDTO dto) throws IOException {

        if (verificarSeArquivoExiste(file.getOriginalFilename())) {
            throw new BusinessException("Arquivo " +  file.getOriginalFilename() + " já existe");
        }

        Curso curso = cursoService.buscarCurso(dto.cursoId());

        String bucket = "bucket-estagio-cursos-livres";
        String folder = dto.tipo().name().toLowerCase();
        String key = dto.cursoId().toString() + "/" + folder + "/" + file.getOriginalFilename();

        s3Service.uploadVideo(bucket, key, file.getInputStream(), file.getSize(), file.getContentType());

        Arquivo arquivo = new Arquivo();
        arquivo.setTitulo(file.getOriginalFilename());
        arquivo.setDescricao(dto.descricao());
        arquivo.setTipoArquivo(dto.tipo());
        arquivo.setS3Key(key);
        arquivo.setContentType(file.getContentType());
        arquivo.setTamanhoBytes(file.getSize());
        arquivo.setAtivo(true);
        arquivo.setDataUpload(LocalDateTime.now());
        arquivo.setCurso(curso);

        arquivo = arquivoRepository.save(arquivo);

        return new NovoArquivoResponseDTO(arquivo);
    }

    public boolean verificarSeArquivoExiste(String nomeArquivo) {

        return arquivoRepository.existsByTitulo(nomeArquivo);
    }

    public ResponseSignedArquivoDTO gerarSignedUrl(RequestSignedArquivoDTO dto) {

        if (!verificarSeArquivoExiste(dto.tituloArquivo())) {
            throw new BusinessException("Arquivo " +  dto.tituloArquivo() + " não existe");
        }

        String bucket = "bucket-estagio-cursos-livres";
        String folder = dto.tipoArquivo().name().toLowerCase();
        String key = dto.cursoId().toString() + "/" + folder + "/" + dto.tituloArquivo();

        String link = s3Service.gerarSignedUrl(bucket, key, Duration.ofMinutes(60));

        return new ResponseSignedArquivoDTO(link);
    }
}

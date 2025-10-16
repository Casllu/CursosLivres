package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.matricula.MatriculaDTO;
import com.estagio.cursosLivres.dto.matricula.NovaMatriculaResponseDTO;
import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.entities.Matricula;
import com.estagio.cursosLivres.entities.Pagamento;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.entities.utils.MatriculaStatus;
import com.estagio.cursosLivres.entities.utils.PagamentoStatus;
import com.estagio.cursosLivres.repositories.CursoRepository;
import com.estagio.cursosLivres.repositories.MatriculaRepository;
import com.estagio.cursosLivres.repositories.PagamentoRepository;
import com.estagio.cursosLivres.repositories.UserRepository;
import com.estagio.cursosLivres.services.exceptions.DatabaseException;
import com.estagio.cursosLivres.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Page<MatriculaDTO> findAll(Pageable pageable) {
        Page<Matricula> matriculas = matriculaRepository.findAll(pageable);

        return matriculas.map(MatriculaDTO :: new);
    }

    public MatriculaDTO findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new MatriculaDTO(matricula);
    }

    public NovaMatriculaResponseDTO novaMatricula(Long alunoId, Long cursoId) {

        Matricula matricula = new Matricula();

        User aluno = userRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setStatus(MatriculaStatus.PAGAMENTO_PENDENTE);
        try {

            matricula =  matriculaRepository.save(matricula);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Aluno já cadastrado nesse curso");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setMatricula(matricula);
        pagamento.setMoment(Instant.now());
        pagamento.setStatus(PagamentoStatus.AGUARDANDO_PAGAMENTO);
        pagamento.setPreco(matricula.getCurso().getPreco());
        pagamento = pagamentoRepository.save(pagamento);

        matricula.setPagamento(pagamento);
        matricula = matriculaRepository.save(matricula);

        return new NovaMatriculaResponseDTO(matricula);
    }

    public void copyDtoToEntity(MatriculaDTO matriculaDTO, Matricula matricula) {
        matricula.setId(matriculaDTO.getId());
        User aluno = new User();
        aluno.setId(matriculaDTO.getAluno().getId());
        matricula.setAluno(aluno);
        Curso curso = new Curso();
        curso.setId(matriculaDTO.getCurso().getId());
        matricula.setCurso(curso);
        matricula.setDataMatricula(matriculaDTO.getDataMatricula());
        matricula.setStatus(matriculaDTO.getStatus());
    }
}

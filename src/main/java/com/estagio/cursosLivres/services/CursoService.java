package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.curso.CursoDTO;
import com.estagio.cursosLivres.dto.curso.CursoOnlineDTO;
import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.entities.CursoOnline;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.repositories.CursoRepository;
import com.estagio.cursosLivres.repositories.UserRepository;
import com.estagio.cursosLivres.services.exceptions.BusinessException;
import com.estagio.cursosLivres.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Page<CursoDTO> findAllPaged(Pageable pageableRequest) {
        Page<Curso> cursos = cursoRepository.findAll(pageableRequest);
        return cursos.map(CursoDTO::new);
    }

    @Transactional(readOnly = true)
    public CursoDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CursoDTO(curso);
    }

    @Transactional
    public CursoOnlineDTO insertCursoOnline(CursoOnlineDTO dto) {
        CursoOnline cursoOnline = new CursoOnline();
        copyCursoOnlineDtoToEntity(dto, cursoOnline);

        if (dto.getProfessor() != null && dto.getProfessor().getId() != null) {
            User user = userRepository.findById(dto.getProfessor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

            if (user.getRoles().stream().noneMatch(role -> "ROLE_PROFESSOR".equals(role.getAuthority()))) {
                throw new BusinessException("Usuário não possui perfil de professor");
            }

            cursoOnline.setProfessor(user);
        }

        cursoOnline = cursoRepository.save(cursoOnline);

        return new CursoOnlineDTO(cursoOnline);
    }

    private void copyCursoOnlineDtoToEntity(CursoOnlineDTO dto, CursoOnline entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setCargaHoraria(dto.getCargaHoraria());
        entity.setPreco(dto.getPreco());
        entity.setCertificadoDisponivel(dto.isCertificadoDisponivel());
        entity.setValidadeDias(dto.getValidadeDias());
        entity.setQtdAulas(dto.getQtdAulas());
        entity.setQtdCapitulos(dto.getQtdCapitulos());
    }

}

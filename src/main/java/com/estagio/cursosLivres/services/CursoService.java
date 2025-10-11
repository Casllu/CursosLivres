package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.curso.CursoDTO;
import com.estagio.cursosLivres.dto.curso.CursoOnlineDTO;
import com.estagio.cursosLivres.dto.curso.CursoPresencialDTO;
import com.estagio.cursosLivres.entities.Curso;
import com.estagio.cursosLivres.entities.CursoOnline;
import com.estagio.cursosLivres.entities.CursoPresencial;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.repositories.CursoRepository;
import com.estagio.cursosLivres.repositories.UserRepository;
import com.estagio.cursosLivres.services.exceptions.BusinessException;
import com.estagio.cursosLivres.services.exceptions.DatabaseException;
import com.estagio.cursosLivres.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<CursoOnlineDTO> findAllOnlinePaged(Pageable pageableRequest) {
        Page<CursoOnline> cursos = cursoRepository.findAllCursoOnline(pageableRequest);
        return cursos.map(CursoOnlineDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CursoPresencialDTO> findAllPresencialPaged(Pageable pageableRequest) {
        Page<CursoPresencial> cursos = cursoRepository.findAllCursoPresencial(pageableRequest);
        return cursos.map(CursoPresencialDTO::new);
    }

    @Transactional(readOnly = true)
    public CursoDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CursoDTO(curso);
    }

    @Transactional(readOnly = true)
    public CursoOnlineDTO findCursoOnlineById(Long id) {
        CursoOnline curso = cursoRepository.findCursoOnlineById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CursoOnlineDTO(curso);
    }

    @Transactional(readOnly = true)
    public CursoPresencialDTO findCursoPresencialById(Long id) {
        CursoPresencial curso = cursoRepository.findCursoPresenciaById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CursoPresencialDTO(curso);
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

    @Transactional
    public CursoPresencialDTO insertCursoPresencial(CursoPresencialDTO dto) {
        CursoPresencial cursoPresencial = new CursoPresencial();
        copyCursoPresencialDtoToEntity(dto, cursoPresencial);

        if (dto.getProfessor() != null && dto.getProfessor().getId() != null) {
            User user = userRepository.findById(dto.getProfessor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

            if (user.getRoles().stream().noneMatch(role -> "ROLE_PROFESSOR".equals(role.getAuthority()))) {
                throw new BusinessException("Usuário não possui perfil de professor");
            }

            cursoPresencial.setProfessor(user);
        }

        cursoPresencial = cursoRepository.save(cursoPresencial);

        return new CursoPresencialDTO(cursoPresencial);

    }

    @Transactional
    public CursoOnlineDTO updateCursoOnline(Long id, CursoOnlineDTO dto) {
        try {
            CursoOnline entity = cursoRepository.findCursoOnlineById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Id: " + id + " não é de um curso online."));

            copyCursoOnlineDtoToEntity(dto, entity);

            entity = cursoRepository.save(entity);

            return new CursoOnlineDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Transactional
    public CursoPresencialDTO updateCursoPresencial(Long id, CursoPresencialDTO dto) {
        try {
            CursoPresencial entity = cursoRepository.findCursoPresenciaById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Id: " + id + " não é de um curso presencial."));

            CursoPresencial cursoPresencial = (CursoPresencial) entity;
            copyCursoPresencialDtoToEntity(dto, cursoPresencial);

            cursoPresencial = cursoRepository.save(cursoPresencial);

            return new CursoPresencialDTO(cursoPresencial);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try {
            cursoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyCursoOnlineDtoToEntity(CursoOnlineDTO dto, CursoOnline entity) {

        if(dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if(dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getCargaHoraria() != null) {
            entity.setCargaHoraria(dto.getCargaHoraria());
        }
        if(dto.getPreco() != null) {
            entity.setPreco(dto.getPreco());
        }
        if(dto.isCertificadoDisponivel() != null) {
            entity.setCertificadoDisponivel(dto.isCertificadoDisponivel());
        }
        if(dto.getValidadeDias() != null) {
            entity.setValidadeDias(dto.getValidadeDias());
        }
        if(dto.getQtdAulas() != null) {
            entity.setQtdAulas(dto.getQtdAulas());
        }
        if(dto.getQtdCapitulos() != null) {
            entity.setQtdCapitulos(dto.getQtdCapitulos());
        }
    }

    private void copyCursoPresencialDtoToEntity(CursoPresencialDTO dto, CursoPresencial entity) {
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getCargaHoraria() != null) {
            entity.setCargaHoraria(dto.getCargaHoraria());
        }
        if (dto.getPreco() != null) {
            entity.setPreco(dto.getPreco());
        }
        if (dto.isCertificadoDisponivel() != null) {
            entity.setCertificadoDisponivel(dto.isCertificadoDisponivel());
        }
        if (dto.getLocal() != null) {
            entity.setLocal(dto.getLocal());
        }
        if (dto.getDataInicio() != null) {
            entity.setDataInicio(dto.getDataInicio());
        }
        if (dto.getDataFim() != null) {
            entity.setDataFim(dto.getDataFim());
        }
        if (dto.getMinAlunos() != null) {
            entity.setMinAlunos(dto.getMinAlunos());
        }
        if (dto.getMaxAlunos() != null) {
            entity.setMaxAlunos(dto.getMaxAlunos());
        }
    }


}

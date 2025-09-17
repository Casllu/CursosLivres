package com.estagio.cursosLivres.services;

import com.estagio.cursosLivres.dto.user.UserUpdateDTO;
import com.estagio.cursosLivres.dto.role.RoleDTO;
import com.estagio.cursosLivres.dto.user.UserDTO;
import com.estagio.cursosLivres.dto.user.UserInsertDTO;
import com.estagio.cursosLivres.entities.Role;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.projections.UserDetailsProjection;
import com.estagio.cursosLivres.repositories.RoleRepository;
import com.estagio.cursosLivres.repositories.UserRepository;
import com.estagio.cursosLivres.services.exceptions.DatabaseException;
import com.estagio.cursosLivres.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageRequest) {
        Page<User> page = userRepository.findAll(pageRequest);
        return page.map(UserDTO :: new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);

        entity.getRoles().clear();
        Role role = roleRepository.findByAuthority("ROLE_ALUNO");
        entity.getRoles().add(role);

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);

            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if( result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.getFirst().getPassword());

        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    public UserDTO findMe() {
        User entity = authService.authenticated();

        return new UserDTO(entity);
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getNome());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }
}

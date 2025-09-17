package com.estagio.cursosLivres.controller;

import com.estagio.cursosLivres.dto.user.UserDTO;
import com.estagio.cursosLivres.dto.user.UserInsertDTO;
import com.estagio.cursosLivres.dto.user.UserUpdateDTO;
import com.estagio.cursosLivres.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageRequest) {

        Page<UserDTO> list = userService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = userService.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
        UserDTO newDto = userService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        UserDTO newDto = userService.update(id, dto);

        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALUNO', 'ROLE_PROF')")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> findMe() {
        UserDTO dto = userService.findMe();

        return ResponseEntity.ok(dto);
    }
}

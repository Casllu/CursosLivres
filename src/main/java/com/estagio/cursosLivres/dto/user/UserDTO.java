package com.estagio.cursosLivres.dto.user;

import com.estagio.cursosLivres.dto.role.RoleDTO;
import com.estagio.cursosLivres.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Email(message = "Favor inserir um email válido")
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String nome, String lastName, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        nome = entity.getFirstName();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String firstName) {
        this.nome = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}

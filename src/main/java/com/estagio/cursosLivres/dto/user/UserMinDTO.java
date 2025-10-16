package com.estagio.cursosLivres.dto.user;

import com.estagio.cursosLivres.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserMinDTO {

    private Long id;
    private String nome;
    private String lastName;

    @Email(message = "Favor inserir um email válido")
    private String email;

    public UserMinDTO(Long id, String nome, String lastName, String email) {
        this.id = id;
        this.nome = nome;
        this.lastName = lastName;
        this.email = email;
    }

    public UserMinDTO(User entity) {
        id = entity.getId();
        nome = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();
    }

    public UserMinDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

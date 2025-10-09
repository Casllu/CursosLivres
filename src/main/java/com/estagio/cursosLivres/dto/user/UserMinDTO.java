package com.estagio.cursosLivres.dto.user;

import com.estagio.cursosLivres.entities.User;
import jakarta.validation.constraints.NotNull;

public class UserMinDTO {

    private Long id;
    private String nome;

    public UserMinDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UserMinDTO(User entity) {
        id = entity.getId();
        nome = entity.getFirstName();
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
}

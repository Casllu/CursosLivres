package com.estagio.cursosLivres.repositories;

import com.estagio.cursosLivres.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}

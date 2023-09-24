package com.foro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.foro.api.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    
    public UserDetails findByNombreUsuario(String username);

}

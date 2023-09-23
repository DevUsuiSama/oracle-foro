package com.foro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.api.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    
}

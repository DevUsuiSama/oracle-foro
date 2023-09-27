package com.foro.api.dto.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.foro.api.model.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank @JsonAlias("nombre_usuario") String nombreUsuario,
        @NotBlank String clave) {

    public UsuarioDTO(UsuarioModel usuarioModel) {
        this(usuarioModel.getNombreUsuario(), usuarioModel.getClave());
    }

}

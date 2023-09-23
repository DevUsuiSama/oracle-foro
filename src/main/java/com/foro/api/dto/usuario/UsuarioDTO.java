package com.foro.api.dto.usuario;

import com.foro.api.models.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank String nombre_usuario,
        @NotBlank String clave) {

    public UsuarioDTO(UsuarioModel usuarioModel) {
        this(usuarioModel.getNombreUsuario(), usuarioModel.getClave());
    }

}

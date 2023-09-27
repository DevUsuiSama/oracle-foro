package com.foro.api.dto.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
        @NotNull int id,
        @JsonAlias("nombre_usuario") String nombreUsuario,
        String clave) {

}

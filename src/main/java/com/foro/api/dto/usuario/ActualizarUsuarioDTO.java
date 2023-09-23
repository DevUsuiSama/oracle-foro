package com.foro.api.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
        @NotNull int id,
        String nombre_usuario,
        String clave) {

}

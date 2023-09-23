package com.foro.api.models;

import com.foro.api.dto.usuario.ActualizarUsuarioDTO;
import com.foro.api.dto.usuario.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity(name = "UsuarioModel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreUsuario;
    private String clave;

    public UsuarioModel(UsuarioDTO usuarioDTO) {
        nombreUsuario = usuarioDTO.nombre_usuario();
        this.clave = usuarioDTO.clave();
    }

    public void actualizar(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        nombreUsuario = actualizarUsuarioDTO.nombre_usuario() == null ? nombreUsuario : actualizarUsuarioDTO.nombre_usuario();
        this.clave = actualizarUsuarioDTO.clave() == null ? this.clave : actualizarUsuarioDTO.clave();
    }

}

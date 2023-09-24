package com.foro.api.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class UsuarioModel implements UserDetails {
    
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

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

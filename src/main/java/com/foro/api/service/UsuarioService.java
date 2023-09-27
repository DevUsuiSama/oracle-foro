package com.foro.api.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.foro.api.dto.usuario.ActualizarUsuarioDTO;
import com.foro.api.dto.usuario.UsuarioDTO;
import com.foro.api.model.UsuarioModel;
import com.foro.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioModel usuarioModel;

    public void registrar(UsuarioDTO usuarioDTO) {
        usuarioModel = new UsuarioModel(usuarioDTO);
        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getPassword()));
        usuarioRepository.save(usuarioModel);
    }

    public UsuarioDTO actualizar(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        usuarioModel = usuarioRepository.getReferenceById(actualizarUsuarioDTO.id());
        usuarioModel.actualizar(actualizarUsuarioDTO);
        return new UsuarioDTO(usuarioModel);
    }

    public List<UsuarioDTO> mostrarTodo() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    public void eliminar(int id) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuarioModel);
    }

    public URI construirURI(UriComponentsBuilder uriComponentsBuilder) {
        return uriComponentsBuilder.path("/login/{id}").buildAndExpand(usuarioModel.getId()).toUri();
    }

}

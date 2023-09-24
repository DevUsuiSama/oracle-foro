package com.foro.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foro.api.dto.usuario.ActualizarUsuarioDTO;
import com.foro.api.dto.usuario.UsuarioDTO;
import com.foro.api.models.UsuarioModel;
import com.foro.api.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioDTO usuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioModel usuarioModel = new UsuarioModel(usuarioDTO);
        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getPassword()));
        usuarioModel = usuarioRepository.save(usuarioModel);
        URI uri = uriComponentsBuilder.path("/login/{id}").buildAndExpand(usuarioModel.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> mostrar() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> actualizar(@RequestBody @Valid ActualizarUsuarioDTO actualizarUsuarioDTO) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(actualizarUsuarioDTO.id());
        usuarioModel.actualizar(actualizarUsuarioDTO);
        return ResponseEntity.ok(new UsuarioDTO(usuarioModel));
    }

    @DeleteMapping(params = "/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuarioModel);
        return ResponseEntity.noContent().build();
    }

}

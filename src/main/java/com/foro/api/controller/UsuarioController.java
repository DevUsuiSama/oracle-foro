package com.foro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.foro.api.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioDTO usuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        usuarioService.registrar(usuarioDTO);
        return ResponseEntity.created(usuarioService.construirURI(uriComponentsBuilder)).body(usuarioDTO);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<UsuarioDTO>> mostrar() {
        return ResponseEntity.ok(usuarioService.mostrarTodo());
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UsuarioDTO> actualizar(@RequestBody @Valid ActualizarUsuarioDTO actualizarUsuarioDTO) {
        return ResponseEntity.ok(usuarioService.actualizar(actualizarUsuarioDTO));
    }

    @DeleteMapping(params = "/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}

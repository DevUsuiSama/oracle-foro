package com.foro.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioRepository.save(new UsuarioModel(usuarioDTO));
    }

    @GetMapping
    public List<UsuarioDTO> mostrar() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid ActualizarUsuarioDTO actualizarUsuarioDTO) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(actualizarUsuarioDTO.id());
        usuarioModel.actualizar(actualizarUsuarioDTO);
    }

    @DeleteMapping(params = "/{id}")
    @Transactional
    public void eliminar(@PathVariable int id) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuarioModel);
    }

}

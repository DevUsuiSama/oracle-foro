package com.foro.api.core.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foro.api.dto.usuario.UsuarioDTO;
import com.foro.api.model.UsuarioModel;
import com.foro.api.core.security.services.TokenService;
import com.foro.api.core.security.dto.TokenDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioDTO.nombreUsuario(), usuarioDTO.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var token = tokenService.generar((UsuarioModel) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

}

package com.foro.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.foro.api.dto.topicos.ActualizarTopicoDTO;
import com.foro.api.dto.topicos.TopicoDTO;
import com.foro.api.service.TopicosService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private TopicosService topicosService;

    @PostMapping
    public ResponseEntity<TopicoDTO> registra(@RequestBody @Valid TopicoDTO topicoDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        return ResponseEntity.created(topicosService.construirURI(uriComponentsBuilder)).body(topicosService.registrar(topicoDTO));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> mostrar() {
        return ResponseEntity.ok(topicosService.mostrarTodo());
    }

    @GetMapping("/consultar_por_fecha/{fecha}")
    public ResponseEntity<List<TopicoDTO>> mostrarPorFecha(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate fecha) {
        return ResponseEntity.ok(topicosService.mostrarPorFecha(fecha));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> mostrarPorID(@PathVariable int id) {
        return ResponseEntity.ok(topicosService.mostrarPorID(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizar(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO) {
        return ResponseEntity.ok(topicosService.actualizar(actualizarTopicoDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        topicosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}

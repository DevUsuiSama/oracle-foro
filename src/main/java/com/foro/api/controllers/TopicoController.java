package com.foro.api.controllers;

import java.net.URI;
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

import com.foro.api.dto.topicos.ActualizarTopicoDTO;
import com.foro.api.dto.topicos.TopicoDTO;
import com.foro.api.models.TopicosModel;
import com.foro.api.repositories.TopicosRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicosRepository topicosRepository;
    
    @PostMapping
    public ResponseEntity<TopicoDTO> registra(@RequestBody @Valid TopicoDTO topicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        TopicosModel topicosModel = topicosRepository.save(new TopicosModel(topicoDTO));
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicosModel.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> mostrar() {
        return ResponseEntity.ok(topicosRepository.findAll().stream().map(TopicoDTO::new).toList());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> mostrarPorID(@PathVariable int id) {
        return ResponseEntity.ok(new TopicoDTO(topicosRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizar(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO) {
        TopicosModel topicosModel = topicosRepository.getReferenceById(actualizarTopicoDTO.id());
        topicosModel.actualizar(actualizarTopicoDTO);
        return ResponseEntity.ok(new TopicoDTO(topicosModel));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        TopicosModel topicosModel = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topicosModel);
        return ResponseEntity.noContent().build();
    }

}

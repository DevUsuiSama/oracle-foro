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
    public void registra(@RequestBody @Valid TopicoDTO topicoDTO) {
        topicosRepository.save(new TopicosModel(topicoDTO));
    }

    @GetMapping
    public List<TopicoDTO> mostrar() {
        return topicosRepository.findAll().stream().map(TopicoDTO::new).toList();
    }

    @GetMapping("/{id}")
    @Transactional
    public TopicoDTO mostrarPorID(@PathVariable int id) {
        return new TopicoDTO(topicosRepository.getReferenceById(id));
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO) {
        TopicosModel topicosModel = topicosRepository.getReferenceById(actualizarTopicoDTO.id());
        topicosModel.actualizar(actualizarTopicoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable int id) {
        TopicosModel topicosModel = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topicosModel);
    }

}

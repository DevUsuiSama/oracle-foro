package com.foro.api.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.foro.api.core.errors.ValidacionDeIntegridad;
import com.foro.api.dto.topicos.ActualizarTopicoDTO;
import com.foro.api.dto.topicos.TopicoDTO;
import com.foro.api.model.TopicosModel;
import com.foro.api.repository.TopicosRepository;

@Service
public class TopicosService {

    @Autowired
    private TopicosRepository topicosRepository;
    private TopicosModel topicosModel;

    public void registrar(TopicoDTO topicoDTO) {
        topicosModel = topicosRepository.save(new TopicosModel(topicoDTO));
    }

    public List<TopicoDTO> mostrarTodo() {
        return topicosRepository.findAll().stream().map(TopicoDTO::new).toList();
    }

    public TopicoDTO mostrarPorID(int id) {
        if (!topicosRepository.findById(id).isPresent())
            throw new ValidacionDeIntegridad("El id del topico buscado no se encontro");
        return new TopicoDTO(topicosRepository.getReferenceById(id));
    }

    public TopicoDTO actualizar(ActualizarTopicoDTO actualizarTopicoDTO) {
        topicosModel = topicosRepository.getReferenceById(actualizarTopicoDTO.id());
        topicosModel.actualizar(actualizarTopicoDTO);
        return new TopicoDTO(topicosModel);
    }

    public void eliminar(int id) {
        topicosModel = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topicosModel);
    }

    public URI construirURI(UriComponentsBuilder uriComponentsBuilder) {
        return uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicosModel.getId()).toUri();
    }

}

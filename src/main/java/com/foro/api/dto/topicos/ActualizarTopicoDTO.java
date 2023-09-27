package com.foro.api.dto.topicos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.model.TopicosModel;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull int id,
        String titulo,
        String mensaje,
        @JsonAlias("fecha_creacion") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate fechaCreacion,
        EstatusEnum estatus,
        String autor,
        CursoEnum curso) {

    public ActualizarTopicoDTO(TopicosModel topicosModel) {
        this(topicosModel.getId(),
                topicosModel.getTitulo(),
                topicosModel.getMensaje(),
                topicosModel.getFechaCreacion(),
                topicosModel.getEstatus(),
                topicosModel.getAutor(),
                topicosModel.getCurso());
    }

}

package com.foro.api.dto.topicos;

import java.sql.Date;

import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.models.TopicosModel;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull int id,
        String titulo,
        String mensaje,
        Date fecha_creacion,
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

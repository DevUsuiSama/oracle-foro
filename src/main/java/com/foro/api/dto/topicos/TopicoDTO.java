package com.foro.api.dto.topicos;

import java.sql.Date;

import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.models.TopicosModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Date fecha_creacion,
        @NotNull EstatusEnum estatus,
        @NotBlank String autor,
        @NotNull CursoEnum curso) {

    public TopicoDTO(TopicosModel topicosModel) {
        this(topicosModel.getTitulo(),
                topicosModel.getMensaje(),
                topicosModel.getFechaCreacion(),
                topicosModel.getEstatus(),
                topicosModel.getAutor(),
                topicosModel.getCurso());
    }

}

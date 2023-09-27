package com.foro.api.dto.topicos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.model.TopicosModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @JsonAlias("fecha_creacion") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate fechaCreacion,
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

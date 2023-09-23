package com.foro.api.models;

import java.sql.Date;

import com.foro.api.dto.topicos.ActualizarTopicoDTO;
import com.foro.api.dto.topicos.TopicoDTO;
import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "TopicosModel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String mensaje;
    private Date fechaCreacion;
    @Enumerated
    private EstatusEnum estatus;
    private String autor;
    @Enumerated
    private CursoEnum curso;

    public TopicosModel(TopicoDTO topicoDTO) {
        this.titulo = topicoDTO.titulo();
        this.mensaje = topicoDTO.mensaje();
        fechaCreacion = topicoDTO.fecha_creacion();
        this.estatus = topicoDTO.estatus();
        this.autor = topicoDTO.autor();
        this.curso = topicoDTO.curso();
    }

    public void actualizar(ActualizarTopicoDTO actualizarTopicoDTO) {
        this.titulo = actualizarTopicoDTO.titulo() == null ? this.titulo : actualizarTopicoDTO.titulo();
        this.mensaje = actualizarTopicoDTO.mensaje() == null ? this.mensaje : actualizarTopicoDTO.mensaje();
        fechaCreacion = actualizarTopicoDTO.fecha_creacion() == null ? this.fechaCreacion : actualizarTopicoDTO.fecha_creacion();
        this.estatus = actualizarTopicoDTO.estatus() == null ? this.estatus : actualizarTopicoDTO.estatus();
        this.autor = actualizarTopicoDTO.autor() == null ? this.autor : actualizarTopicoDTO.autor();
        this.curso = actualizarTopicoDTO.curso() == null ? this.curso : actualizarTopicoDTO.curso();
    }

}

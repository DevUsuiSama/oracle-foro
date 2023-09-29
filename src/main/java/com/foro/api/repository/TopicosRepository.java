package com.foro.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foro.api.model.TopicosModel;

@Repository
public interface TopicosRepository extends JpaRepository<TopicosModel, Integer> {

    @Query(value = """
            SELECT
                *
            FROM
                topicos m
            WHERE
                m.fecha_creacion=:fecha
            ORDER BY m.id
            """, nativeQuery = true)
    public List<TopicosModel> seleccionarTodosLosTopicosConFecha(LocalDate fecha);

}

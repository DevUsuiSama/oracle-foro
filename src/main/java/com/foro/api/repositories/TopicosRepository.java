package com.foro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.api.models.TopicosModel;

public interface TopicosRepository extends JpaRepository<TopicosModel, Integer> {
    
}

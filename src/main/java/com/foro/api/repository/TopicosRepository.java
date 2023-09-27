package com.foro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foro.api.model.TopicosModel;

@Repository
public interface TopicosRepository extends JpaRepository<TopicosModel, Integer> {
    
}

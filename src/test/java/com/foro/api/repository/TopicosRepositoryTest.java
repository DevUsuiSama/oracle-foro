package com.foro.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.model.TopicosModel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TopicosRepositoryTest {

    @Autowired
    private TopicosRepository topicosRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    TopicosModel registrarTopico() {
        TopicosModel topicosModel = new TopicosModel(
                0,
                "c++ o rust",
                "A dia de hoy uno de los debates mas controversial",
                LocalDate.of(2022, 10, 25),
                EstatusEnum.RESUELTO,
                "peter",
                CursoEnum.G3);
        return testEntityManager.persist(topicosModel);
    }

    @Test
    @DisplayName("deberia retornar un topicos cuando se realize la consulta con la fecha")
    void testSeleccionarTodosLosTopicosConFecha() {
        List<TopicosModel> rTopicosModel = new ArrayList<TopicosModel>();
        rTopicosModel.add(registrarTopico());
        var date = LocalDate.of(2022, 10, 25);
        var topicosModel = topicosRepository.seleccionarTodosLosTopicosConFecha(date);
        assertEquals(rTopicosModel, topicosModel);
    }

}

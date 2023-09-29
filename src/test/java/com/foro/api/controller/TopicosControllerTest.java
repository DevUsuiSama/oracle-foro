package com.foro.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.net.URI;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.foro.api.dto.topicos.TopicoDTO;
import com.foro.api.enums.CursoEnum;
import com.foro.api.enums.EstatusEnum;
import com.foro.api.service.TopicosService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TopicosControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<TopicoDTO> topicosJacksonTester;
    @MockBean
    private TopicosService topicosService;

    @Test
    @DisplayName("deberia retornar estado http 400, cuando los datos ingresados sean invalidos")
    @WithMockUser
    void testRegistra1() throws Exception {
        var response = mockMvc.perform(post(new URI("/topicos"))).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("deberia retornar estado http 200, cuando los datos ingresados sean validos")
    @WithMockUser
    void testRegistra2() throws Exception {
        var datos = new TopicoDTO("c++ o rust",
                "A dia de hoy uno de los debates mas controversial",
                LocalDate.of(2022, 10, 25),
                EstatusEnum.ACTIVO,
                "peter",
                CursoEnum.G3);
        when(topicosService.registrar(any())).thenReturn(datos);
        var response = mockMvc.perform(post(new URI("/topicos"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(topicosJacksonTester.write(new TopicoDTO("c++ o rust",
                        "A dia de hoy uno de los debates mas controversial",
                        LocalDate.of(2022, 10, 25),
                        EstatusEnum.ACTIVO,
                        "peter",
                        CursoEnum.G3)).getJson()))
                .andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        var jsonEsperado = topicosJacksonTester.write(datos).getJson();
        assertEquals(jsonEsperado, response.getContentAsString());
    }

}

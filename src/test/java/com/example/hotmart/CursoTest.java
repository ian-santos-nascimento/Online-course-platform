package com.example.hotmart;

import com.example.hotmart.curso.model.Curso;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SpringJUnitConfig
public class CursoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCurso() throws Exception {
        // Create a sample Curso object for the request body
        Curso curso = Curso.builder().id("1")
                .titulo("Teste titulo")
                .descricao("Teste descricao")
                .alunos(1000)
                .build();
        String cursoJson = objectMapper.writeValueAsString(curso);
        // Perform a POST request to the "/curso" endpoint with the Curso object as JSON in the request body
        mockMvc.perform(MockMvcRequestBuilders.post("/curso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cursoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(curso.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value(curso.getTitulo()));
    }

}

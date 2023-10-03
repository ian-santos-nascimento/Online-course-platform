package com.example.hotmart;

import com.example.hotmart.usuario.model.Usuario;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SpringJUnitConfig
public class UsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Usuario usuario = Usuario.builder()
            .id("1")
            .email("ian@gmail.com")
            .nome("Ian Santos")
            .senha("123") //TODO change when implement SpringSecurity
            .build();

    @Test
    public void testCreateUsuario() throws Exception {
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(usuario.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuario.getEmail()));

    }

    @Test
    public void testUpadteUsuario() throws Exception {
        Usuario updateUsuario = this.usuario;
        updateUsuario.setNome("Ian Nascimento");
        String usuarioJson = objectMapper.writeValueAsString(updateUsuario);
        mockMvc.perform(MockMvcRequestBuilders.put("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testFindUsuario() throws Exception {
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        mockMvc.perform(MockMvcRequestBuilders.get("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuario.getEmail()));

    }


}

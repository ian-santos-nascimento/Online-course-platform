package com.example.hotmart.curso.model;

import com.example.hotmart.usuario.model.Usuario;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("avaliacao_curso")
public class AvaliacaoCurso implements Serializable {

    @Id
    private String id;

    private String comentario;

    private Double estrelas;

    @DBRef
    private Usuario usuario;

    @DBRef
    private Curso curso;


}

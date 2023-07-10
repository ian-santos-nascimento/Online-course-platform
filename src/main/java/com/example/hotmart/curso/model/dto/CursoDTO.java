package com.example.hotmart.curso.model.dto;

import com.example.hotmart.curso.model.Secao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class CursoDTO {

    private String id;

    private String titulo;

    private String descricao;

    private String nomeUsuario;

    private String emailUsuario;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private String dataCriacao;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private String dataAtualizacao;

    //private List<Secao>

    private int alunos;

}

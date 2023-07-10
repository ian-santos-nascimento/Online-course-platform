package com.example.hotmart.curso.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
@Document
public class Secao implements Serializable {

    @Id
    private String id;

    private String titulo;

    private Long duracaoTotal; //Em segundos

    @DBRef
    private Curso curso;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getDuracaoTotal() {
        return duracaoTotal;
    }

    public void setDuracaoTotal(Long duracaoTotal) {
        this.duracaoTotal = duracaoTotal;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Secao{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", duracaoTotal=" + duracaoTotal +
                ", curso=" + curso +
                '}';
    }
}

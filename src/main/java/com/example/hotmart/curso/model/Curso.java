package com.example.hotmart.curso.model;

import com.example.hotmart.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "curso")
@Builder
public class Curso implements Serializable {

    @Id
    private String id;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private String dataCriacao;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private String dataAtualizacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataRemocao;

    private String titulo;

    private String descricao;

    private int alunos;

    private List<Secao> secaoList;
    @DBRef
    private Usuario usuario;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(String dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }

    public List<Secao> getSecaoList() {
        return secaoList;
    }

    public void setSecaoList(List<Secao> secaoList) {
        this.secaoList = secaoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void addSecao(Secao secao){
        if(secaoList == null){
            secaoList = new ArrayList<>();
        }
        secaoList.add(secao);
    }

}



package com.example.hotmart.curso.model;

import com.example.hotmart.curso.model.Secao;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Document("video")
@Builder
public class Video implements Serializable {

    @Id
    private String id;

    private byte[] bytes;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

    private String titulo;

    private int realLength;


    private String secao;

    public Video() {

    }

    public Video(String id, byte[] bytes, Date dataCriacao, String titulo, int realLength, String secao) {
        this.id = id;
        this.bytes = bytes;
        this.dataCriacao = dataCriacao;
        this.titulo = titulo;
        this.realLength = realLength;
        this.secao = secao;
    }

    public int getRealLength() {
        return realLength;
    }

    public void setRealLength(int realLength) {
        this.realLength = realLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}

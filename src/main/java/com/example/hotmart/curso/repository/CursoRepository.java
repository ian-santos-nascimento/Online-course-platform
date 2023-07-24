package com.example.hotmart.curso.repository;

import com.example.hotmart.curso.model.Curso;
import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.usuario.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CursoRepository extends MongoRepository<Curso, String> {

    @Query(fields = "{ usuario: { senha:  0, dataCriacao: 0}, secaoList: {curso:  0 }}")
    public List<Curso> findAllByUsuario(Usuario usuario);

    @Query(fields = "{ usuario: { senha:  0, dataCriacao: 0}, secaoList: {curso:  0 }}")
    public Optional<Curso> findCursoById(String id);

    @Query("{id: ?0}")
    @Update("{ '$set' : { 'dataRemocao' : ?1 } }")
    public void deleteCurso(String id, Date data);
    @Query("{id: ?0}")
    @Update("{ '$set' : { 'secaoList' : ?1 } }")
    public void updateSecaoList(String id,List<Secao> secaoList);



}


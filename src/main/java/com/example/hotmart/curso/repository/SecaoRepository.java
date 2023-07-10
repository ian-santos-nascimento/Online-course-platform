package com.example.hotmart.curso.repository;

import com.example.hotmart.curso.model.Secao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SecaoRepository extends MongoRepository<Secao, String> {
    @Query(fields = "{curso: 0}")
    public Optional<List<Secao>> findAllByCursoId(String cursoId);

    public Optional<Secao> findById(String id);
}

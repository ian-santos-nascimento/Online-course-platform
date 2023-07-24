package com.example.hotmart.usuario.repository;

import com.example.hotmart.usuario.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{id: ?0}")
    public Optional<Usuario> findById(String id);
    public Optional<Usuario> findByEmail(String email);

    @Query("{email: ?0}")
    @Update("{ '$set' : { 'nome' : ?1 , 'senha' : ?2} }")
    public void updateUser(String email,String nome,String senha);

}

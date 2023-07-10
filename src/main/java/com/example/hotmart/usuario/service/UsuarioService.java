package com.example.hotmart.usuario.service;

import com.example.hotmart.exceptionHandler.ResourceNotFoundException;
import com.example.hotmart.usuario.repository.UsuarioRepository;
import com.example.hotmart.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //@Autowired
    //private GridFsOperations gridFsOperations;

    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
    }

    public Usuario saveUsuario(Usuario usuario){
        usuario.setDataCriacao(new Date());
        return usuarioRepository.save(usuario);
    }

    public void updateUsuario(Usuario usuario){
        var usuarioBanco = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado") );
        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setSenha(usuario.getSenha());
         usuarioRepository.updateUser(usuario.getEmail(),usuario.getNome(), usuario.getSenha());
    }
}

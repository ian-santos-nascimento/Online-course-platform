package com.example.hotmart.curso.service;

import com.example.hotmart.curso.model.Curso;
import com.example.hotmart.curso.repository.CursoRepository;
import com.example.hotmart.util.exceptionHandler.ResourceNotFoundException;
import com.example.hotmart.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.hotmart.util.DataConverter.formatDate;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Curso> listAllCursos() {
        return cursoRepository.findAll();
    }

    public List<Curso> listAllCursosByUsuario(String usuarioId){
        var usuario = usuarioService.findUsuarioById(usuarioId);
        return cursoRepository.findAllByUsuario(usuario);
    }

    public Curso findCursoById(String id) {
        return cursoRepository.findCursoById(id).orElseThrow(()-> new ResourceNotFoundException("Curso não encontrado"));
    }

    public ResponseEntity<Void> deleteCurso(String id) {
        cursoRepository.deleteCurso(id, new Date());
        return ResponseEntity.noContent().build();
    }

    public Curso createCurso(Curso curso) {
        String now = formatDate(new Date());
        curso.setDataCriacao(now);
        curso.setDataAtualizacao(now);
        return cursoRepository.save(curso);
    }
}

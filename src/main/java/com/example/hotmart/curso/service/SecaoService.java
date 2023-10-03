package com.example.hotmart.curso.service;

import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.curso.repository.CursoRepository;
import com.example.hotmart.curso.repository.SecaoRepository;
import com.example.hotmart.util.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository secaoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Secao> findSecaoListByCurso(String cursoId) {
        return secaoRepository.findAllByCursoId(cursoId).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
    }

    public Secao findSecaoById(String secaoId){
        return secaoRepository.findById(secaoId).orElse(null);
    }
    public Secao saveSecao(Secao secao, String cursoId) {
        var cursoDB = cursoRepository.findCursoById(cursoId);
        var secaoDB = secaoRepository.save(secao);
        cursoDB.get().addSecao(secaoDB);
        cursoRepository.updateSecaoList(cursoDB.get().getId(),cursoDB.get().getSecaoList());
        return secaoDB;
    }

    public void update(Secao secao) {
        var secaoDB = secaoRepository.findById(secao.getId());
        secaoDB.ifPresentOrElse(value -> {
                    value.setTitulo(secao.getTitulo());
                    value.setDuracaoTotal(secao.getDuracaoTotal());
                },
                () -> {
                    throw new ResourceNotFoundException(" Seção não encontrada");
                });
    }

    public void delete(String secaoId) {
        secaoRepository.findById(secaoId).ifPresentOrElse((secao) -> secaoRepository.delete(secao),
                () -> {
           throw  new ResourceNotFoundException("Seção não encontrada");
        });
    }
}

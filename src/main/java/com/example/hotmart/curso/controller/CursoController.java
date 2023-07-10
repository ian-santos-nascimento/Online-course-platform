package com.example.hotmart.curso.controller;

import com.example.hotmart.curso.model.Curso;
import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.curso.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all Courses passing the creator email on body",
            description = "List all Courses passing the creator email on body",tags = {"Curso"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Curso.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<List<Curso>> listAllCursos(@RequestBody String email){
        var cursoList = cursoService.listAllCursos(email);
        return ResponseEntity.ok().body(cursoList);
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Search course passing the ID on path",
            description = "Search course passing the ID on path",tags = {"Curso"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Curso.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Curso> findCursoById(@PathVariable  String id){
        var curso= cursoService.findCursoById(id);
        return ResponseEntity.ok().body(curso);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete course passing the ID on path",
            description = "Delete course passing the ID on path",tags = {"Curso"}, responses = {
            @ApiResponse(description = "success", responseCode = "201", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Void> deleteCurso(@PathVariable String id){
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create course passing the ID on path",
            description = "Create course passing the ID on path",tags = {"Curso"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Curso.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso){
        var cursoBD = cursoService.createCurso(curso);
        return ResponseEntity.ok().body(cursoBD);
    }
}

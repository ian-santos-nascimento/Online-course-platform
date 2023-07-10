package com.example.hotmart.curso.controller;

import com.example.hotmart.converter.SecaoConverter;
import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.curso.model.Video;
import com.example.hotmart.curso.service.SecaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/secao")
public class SecaoController {

    @Autowired
    private SecaoService secaoService;

    @GetMapping(path = "{cursoId}")
    @PostMapping(path ="/{id}" )
    @Operation(summary = "Get a section",description = "Get a section  passing the cursoId that the Section is related",tags = {"Secao"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Secao.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public List<Secao> findSecaoListByCurso(@PathVariable  String cursoId){
        return secaoService.findSecaoListByCurso(cursoId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save a section",description = "Save a section ",tags = {"Secao"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Secao.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Secao> saveSecao(@RequestBody Secao secao){
        var secaoDB = secaoService.saveSecao(secao);
        return ResponseEntity.ok().body(secaoDB);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a section",description = "Update a section",tags = {"Secao"}, responses = {
            @ApiResponse(description = "success", responseCode = "201", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Secao.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Void> updateSecao(@RequestBody Secao secao){
        secaoService.update(secao);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Delete a section",description = "Delete a section",tags = {"Secao"}, responses = {
            @ApiResponse(description = "success", responseCode = "201", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Secao.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSecao(@PathVariable("id") String secaoId){
        secaoService.delete(secaoId);
        return ResponseEntity.noContent().build();

    }
}

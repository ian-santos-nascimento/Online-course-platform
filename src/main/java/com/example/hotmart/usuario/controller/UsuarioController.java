package com.example.hotmart.usuario.controller;

import com.example.hotmart.usuario.model.Usuario;
import com.example.hotmart.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search user passing the email on body",
            description = "Search user passing the email on body",tags = {"Usuario"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public @ResponseBody ResponseEntity<Usuario> findUsuarioBy(@RequestBody Usuario user ){
        var usuario =  usuarioService.findUsuarioByEmail(user);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save user ",
            description = "Save user ",tags = {"Usuario"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        var usuarioDB =usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().body(usuarioDB);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update user ",
            description = "Update user ",tags = {"Usuario"}, responses = {
            @ApiResponse(description = "success", responseCode = "201", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario){
        usuarioService.updateUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

}

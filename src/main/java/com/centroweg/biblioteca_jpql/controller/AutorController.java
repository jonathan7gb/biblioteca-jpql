package com.centroweg.biblioteca_jpql.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> criarAutor(
        @Valid @RequestBody AutorRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAutor(dto));
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> obterTodosAutores() {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTodosAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> obterAutorPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterAutorPorId(id));
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<LivroItemResponseDTO>> obterLivrosPorAutorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTodosLivrosPorAutorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAutor(@PathVariable Long id) {
        service.deletarAutor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Autor deletado com sucesso");
    }
}

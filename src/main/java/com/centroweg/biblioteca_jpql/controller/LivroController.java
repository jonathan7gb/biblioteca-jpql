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

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criarLivro(
        @Valid @RequestBody LivroRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarLivro(dto));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> obterTodasLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTodosLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> obterLivroPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterLivroPorId(id));
    }

    @GetMapping("/{id}/autores")
    public ResponseEntity<List<AutorItemResponseDTO>> obterAutoresPorLivroId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTodosAutoresPorLivroId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Livro deletado com sucesso");
    }

}

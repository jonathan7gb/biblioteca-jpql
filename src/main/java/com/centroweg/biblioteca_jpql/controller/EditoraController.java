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

import com.centroweg.biblioteca_jpql.dto.editora.EditoraRequestDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraResponseDTO;
import com.centroweg.biblioteca_jpql.service.EditoraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraService service;

    public EditoraController(EditoraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EditoraResponseDTO> criarEditora(
        @Valid @RequestBody EditoraRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarEditora(dto));
    }

    @GetMapping
    public ResponseEntity<List<EditoraResponseDTO>> obterTodasEditoras() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodasEditoras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> obterEditoraPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarEditoraPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEditora(@PathVariable Long id) {
        service.deletarEditora(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Editora deletada com sucesso");
    }

}

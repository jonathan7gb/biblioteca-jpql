package com.centroweg.biblioteca_jpql.dto.editora;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;

public record EditoraResponseDTO(
    Long id,
    String nome, 
    List<LivroItemResponseDTO> livros
) {

}

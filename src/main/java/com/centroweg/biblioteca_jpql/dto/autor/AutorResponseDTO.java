package com.centroweg.biblioteca_jpql.dto.autor;

import java.time.LocalDateTime;
import java.util.List;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;

public record AutorResponseDTO(
    Long id,
    String nome,
    String nacionalidade,
    LocalDateTime dataNascimento,
    List<LivroItemResponseDTO> livros
) {

}

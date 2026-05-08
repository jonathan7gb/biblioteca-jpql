package com.centroweg.biblioteca_jpql.dto.autor;

import java.time.LocalDate;
import java.util.List;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;

public record AutorResponseDTO(
    Long id,
    String nome,
    String nacionalidade,
    LocalDate dataNascimento,
    List<LivroItemResponseDTO> livros
) {

}

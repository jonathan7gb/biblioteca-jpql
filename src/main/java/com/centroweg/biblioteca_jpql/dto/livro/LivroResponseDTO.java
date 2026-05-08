package com.centroweg.biblioteca_jpql.dto.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraItemResponseDTO;

public record LivroResponseDTO(
    Long id,
    String titulo,
    String isbn,
    BigDecimal preco,
    LocalDate dataPublicacao,
    String categoria,
    EditoraItemResponseDTO editora,
    List<AutorItemResponseDTO> autores
) {
}

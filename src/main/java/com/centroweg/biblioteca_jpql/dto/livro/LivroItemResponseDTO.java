package com.centroweg.biblioteca_jpql.dto.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;


public record LivroItemResponseDTO(
    String titulo,
    String isbn,
    BigDecimal preco,
    LocalDate dataPublicacao,
    String categoria,
    List<AutorItemResponseDTO> autores
) {

}

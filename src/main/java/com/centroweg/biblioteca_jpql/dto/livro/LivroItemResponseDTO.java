package com.centroweg.biblioteca_jpql.dto.livro;

import java.math.BigDecimal;
import java.time.LocalDate;


public record LivroItemResponseDTO(
    String titulo,
    String isbn,
    BigDecimal preco,
    LocalDate dataPublicacao,
    String categoria
) {

}

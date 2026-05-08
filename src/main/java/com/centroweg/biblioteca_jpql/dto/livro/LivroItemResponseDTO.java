package com.centroweg.biblioteca_jpql.dto.livro;

import java.math.BigDecimal;
import java.time.LocalDate;


public record LivroItemResponseDTO(
    String titulo,
    String isbn,
    LocalDate dataPublicacao,
    BigDecimal preco,
    String categoria
) {

}

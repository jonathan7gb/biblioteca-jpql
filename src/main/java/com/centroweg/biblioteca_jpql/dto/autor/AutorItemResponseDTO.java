package com.centroweg.biblioteca_jpql.dto.autor;

import java.time.LocalDate;

public record AutorItemResponseDTO(
    String nome,
    String nacionalidade,
    LocalDate dataNascimento
) {

}

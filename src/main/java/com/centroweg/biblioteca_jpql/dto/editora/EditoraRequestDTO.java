package com.centroweg.biblioteca_jpql.dto.editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditoraRequestDTO(
    @NotBlank(message = "O nome da editora é obrigatório")
    @NotNull(message = "O nome da editora não pode ser nulo")
    String nome
) {

}

package com.centroweg.biblioteca_jpql.dto.editora;

import jakarta.validation.constraints.NotBlank;

public record EditoraRequestDTO(
    @NotBlank(message = "O nome da editora é obrigatório")
    String nome
) {

}

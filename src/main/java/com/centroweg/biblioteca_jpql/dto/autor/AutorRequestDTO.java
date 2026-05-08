package com.centroweg.biblioteca_jpql.dto.autor;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record AutorRequestDTO(
    @NotBlank(message = "O nome do autor é obrigatório")
    @NotNull(message = "O nome do autor não pode ser nulo")
    String nome, 
    
    @NotBlank(message = "A nacionalidade do autor é obrigatória")
    @NotNull(message = "A nacionalidade do autor não pode ser nula")
    String nacionalidade,

    @Past(message = "A data de nascimento deve ser no passado")
    @NotNull(message = "A data de nascimento do autor não pode ser nula")
    LocalDate dataNascimento
) {

}

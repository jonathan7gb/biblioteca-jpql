package com.centroweg.biblioteca_jpql.dto.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record LivroRequestDTO(
    @NotBlank(message = "O título do livro é obrigatório")
    @NotNull(message = "O título do livro não pode ser nulo")
    String titulo,

    @NotBlank(message = "O ISBN do livro é obrigatório")
    @NotNull(message = "O ISBN do livro não pode ser nulo")
    String isbn,

    @NotBlank(message = "O preço do livro é obrigatório")
    @NotNull(message = "O preço do livro não pode ser nulo")
    @Min(value = 0, message = "O preço do livro deve ser um valor positivo")
    BigDecimal preco,

    @Past(message = "A data de publicação deve ser no passado")
    @NotNull(message = "A data de publicação do livro não pode ser nula")
    LocalDate dataPublicacao,

    @NotBlank(message = "A categoria do livro é obrigatória")
    @NotNull(message = "A categoria do livro não pode ser nula")
    String categoria
) {

}

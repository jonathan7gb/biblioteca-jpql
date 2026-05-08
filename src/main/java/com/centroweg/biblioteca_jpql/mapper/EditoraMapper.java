package com.centroweg.biblioteca_jpql.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.centroweg.biblioteca_jpql.dto.editora.EditoraItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraRequestDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraResponseDTO;
import com.centroweg.biblioteca_jpql.model.Editora;

@Component
public class EditoraMapper {

    private final LivroMapper livroMapper;

    public EditoraMapper(@Lazy LivroMapper livroMapper) {
        this.livroMapper = livroMapper;
    }

    public Editora toEntity(EditoraRequestDTO dto) {
        return new Editora(dto.nome());
    }

    public EditoraResponseDTO toDto(Editora editora) {
        if (editora == null) {
            return null;
        }

        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getLivros().stream()
                        .map(livro -> livroMapper.toItemDto(livro))
                        .toList());
    }

    public EditoraItemResponseDTO toItemDto(Editora editora) {
        if (editora == null) {
            return null;
        }

        return new EditoraItemResponseDTO(
                editora.getNome());
    }

}

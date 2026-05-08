package com.centroweg.biblioteca_jpql.mapper;

import org.springframework.stereotype.Component;

import com.centroweg.biblioteca_jpql.dto.editora.EditoraItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraRequestDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraResponseDTO;
import com.centroweg.biblioteca_jpql.model.Editora;

@Component
public class EditoraMapper {

    private LivroMapper livroMapper;

    public Editora toEntity(EditoraRequestDTO dto){
        return new Editora(dto.nome());
    }

    public EditoraResponseDTO toDto(Editora editora){
        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getLivros().stream()
                        .map(livro -> livroMapper.toItemDto(livro))
                        .toList()
        );
    }

    public EditoraItemResponseDTO toItemDto(Editora editora){
        return new EditoraItemResponseDTO(
                editora.getNome()
        );
    }

}

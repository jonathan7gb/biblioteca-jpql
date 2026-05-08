package com.centroweg.biblioteca_jpql.mapper;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.editora.EditoraItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraRequestDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.model.Editora;

public class EditoraMapper {

    public Editora toEntity(EditoraRequestDTO dto){
        return new Editora(dto.nome());
    }

    public EditoraResponseDTO toDto(Editora editora, List<LivroItemResponseDTO> livros){
        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                livros
        );
    }

    public EditoraItemResponseDTO toItemDto(Editora editora){
        return new EditoraItemResponseDTO(
                editora.getNome()
        );
    }

}

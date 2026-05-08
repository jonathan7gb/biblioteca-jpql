package com.centroweg.biblioteca_jpql.mapper;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraItemResponseDTO;
import com.centroweg.biblioteca_jpql.model.Livro;

public class LivroMapper {

        public Livro toEntity(com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO dto){
        return new Livro(
            dto.titulo(), 
            dto.isbn(), 
            dto.dataPublicacao(), 
            dto.preco(), 
            dto.categoria()
        );
    }

    public LivroResponseDTO toDto(Livro livro, EditoraItemResponseDTO editora, List<AutorItemResponseDTO> autores){
        return new LivroResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getPreco(),
            livro.getDataPublicacao(),
            livro.getCategoria(),
            editora,
            autores
        );
    }

    public LivroItemResponseDTO toItemDto(Livro livro){
        return new LivroItemResponseDTO(
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getDataPublicacao(),
                livro.getPreco(),
                livro.getCategoria()
        );
    }

}

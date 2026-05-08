package com.centroweg.biblioteca_jpql.mapper;

import java.util.Collections;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.model.Livro;

@Component
public class LivroMapper {

    private final AutorMapper autorMapper;
    private final EditoraMapper editoraMapper;

    public LivroMapper(@Lazy AutorMapper autorMapper, @Lazy EditoraMapper editoraMapper) {
        this.autorMapper = autorMapper;
        this.editoraMapper = editoraMapper;
    }

    public Livro toEntity(com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO dto) {
        return new Livro(
                dto.titulo(),
                dto.isbn(),
                dto.dataPublicacao(),
                dto.preco(),
                dto.categoria());
    }

    public LivroResponseDTO toDto(Livro livro) {
        if (livro == null) {
            return null;
        }

        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getDataPublicacao(),
                livro.getCategoria(),
                editoraMapper.toItemDto(livro.getEditora()),
                livro.getAutores() == null ? Collections.emptyList() : 
                livro.getAutores().stream()
                    .map(autor -> autorMapper.toItemDto(autor))
                    .toList());
    }

    public LivroItemResponseDTO toItemDto(Livro livro) {
        if (livro == null) {
            return null;
        }

        return new LivroItemResponseDTO(
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getDataPublicacao(),
                livro.getPreco(),
                livro.getCategoria());
    }

}

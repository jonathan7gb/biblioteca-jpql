package com.centroweg.biblioteca_jpql.mapper;

import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.model.Livro;

public class LivroMapper {

    private AutorMapper autorMapper;
    private EditoraMapper editoraMapper;

    public Livro toEntity(com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO dto) {
        return new Livro(
                dto.titulo(),
                dto.isbn(),
                dto.dataPublicacao(),
                dto.preco(),
                dto.categoria());
    }

    public LivroResponseDTO toDto(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getDataPublicacao(),
                livro.getCategoria(),
                editoraMapper.toItemDto(livro.getEditora()),
                livro.getAutores().stream()
                        .map(autor -> autorMapper.toItemDto(autor))
                        .toList());
    }

    public LivroItemResponseDTO toItemDto(Livro livro) {
        return new LivroItemResponseDTO(
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getDataPublicacao(),
                livro.getPreco(),
                livro.getCategoria());
    }

}

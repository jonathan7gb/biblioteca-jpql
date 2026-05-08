package com.centroweg.biblioteca_jpql.mapper;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.model.Autor;

public class AutorMapper {

    private LivroMapper livroMapper;

    public Autor toEntity(AutorRequestDTO dto){
        return new Autor(dto.nome(), dto.nacionalidade(), dto.dataNascimento());
    }

    public AutorResponseDTO toDto(Autor autor){
        return new AutorResponseDTO(
            autor.getId(),
            autor.getNome(),
            autor.getNacionalidade(),
            autor.getDataNascimento(),
            autor.getLivros().stream()
            .map(livro -> livroMapper.toItemDto(livro))
            .toList()
        );
    }

    public AutorItemResponseDTO toItemDto(Autor autor){
        return new AutorItemResponseDTO(
            autor.getNome(),
            autor.getNacionalidade(),
            autor.getDataNascimento()
        );
    }

}

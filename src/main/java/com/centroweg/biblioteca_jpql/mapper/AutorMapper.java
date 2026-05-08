package com.centroweg.biblioteca_jpql.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.model.Autor;

@Component
public class AutorMapper {

    private final LivroMapper livroMapper;

    public AutorMapper(@Lazy LivroMapper livroMapper) {
        this.livroMapper = livroMapper;
    }

    public Autor toEntity(AutorRequestDTO dto){
        return new Autor(dto.nome(), dto.nacionalidade(), dto.dataNascimento());
    }

    public AutorResponseDTO toDto(Autor autor){
        if(autor == null){
            return null;
        }

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
        if(autor == null){
            return null;
        }

        return new AutorItemResponseDTO(
            autor.getNome(),
            autor.getNacionalidade(),
            autor.getDataNascimento()
        );
    }

}

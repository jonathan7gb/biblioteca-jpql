package com.centroweg.biblioteca_jpql.mapper;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.model.Autor;

public class AutorMapper {

    public Autor toEntity(AutorRequestDTO dto){
        return new Autor(dto.nome(), dto.nacionalidade(), dto.dataNascimento());
    }

    public AutorResponseDTO toDto(Autor autor, List<LivroItemResponseDTO> livros){
        return new AutorResponseDTO(
            autor.getId(),
            autor.getNome(),
            autor.getNacionalidade(),
            autor.getDataNascimento(),
            livros
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

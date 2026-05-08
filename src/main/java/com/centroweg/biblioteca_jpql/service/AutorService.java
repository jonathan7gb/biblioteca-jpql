package com.centroweg.biblioteca_jpql.service;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.mapper.AutorMapper;
import com.centroweg.biblioteca_jpql.model.Autor;
import com.centroweg.biblioteca_jpql.repository.AutorRepository;

public class AutorService {

    private final AutorRepository repository;
    private final AutorMapper mapper;

    public AutorService(AutorRepository repository, AutorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AutorResponseDTO criarAutor(AutorRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)), null);
    }

    public AutorResponseDTO obterAutorPorId(Long id) {
        Autor autor = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return mapper.toDto(autor, null);
    }

    public List<AutorResponseDTO> obterTodosAutores() {
       List<Autor> autores = repository.findAll();
       return autores.stream()
        .map(autor -> mapper.toDto(autor, null))
        .toList();
    }

    public void deletarAutor(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Autor não encontrado");
        }
        repository.deleteById(id);
    }
}

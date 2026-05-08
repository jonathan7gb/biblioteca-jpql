package com.centroweg.biblioteca_jpql.service;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorRequestDTO;
import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroItemResponseDTO;
import com.centroweg.biblioteca_jpql.mapper.AutorMapper;
import com.centroweg.biblioteca_jpql.mapper.LivroMapper;
import com.centroweg.biblioteca_jpql.model.Autor;
import com.centroweg.biblioteca_jpql.model.Livro;
import com.centroweg.biblioteca_jpql.repository.AutorRepository;
import com.centroweg.biblioteca_jpql.repository.LivroRepository;

public class AutorService {

    private final AutorRepository repository;
    private final AutorMapper mapper;
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public AutorService(AutorRepository repository, AutorMapper mapper, LivroRepository livroRepository, LivroMapper livroMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public AutorResponseDTO criarAutor(AutorRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public AutorResponseDTO obterAutorPorId(Long id) {
        Autor autor = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return mapper.toDto(autor);
    }
    
    public List<AutorResponseDTO> obterTodosAutores() {
       List<Autor> autores = repository.findAll();

       for(Autor autor: autores){
        List<Livro> livros = livroRepository.findAllByAutoresId(autor.getId());
        autor.setLivros(livros);
       }

       return autores.stream()
        .map(autor -> mapper.toDto(autor))
        .toList();
    }

    public List<LivroItemResponseDTO> obterTodosLivrosPorAutorId(Long autoresId) {
       List<Livro> livros = livroRepository.findAllByAutoresId(autoresId);
       return livros.stream()
        .map(livro -> livroMapper.toItemDto(livro))
        .toList();
    }

    public void deletarAutor(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Autor não encontrado");
        }
        repository.deleteById(id);
    }

}

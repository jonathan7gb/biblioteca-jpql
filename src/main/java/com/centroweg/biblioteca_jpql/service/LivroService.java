package com.centroweg.biblioteca_jpql.service;

import java.util.List;

import com.centroweg.biblioteca_jpql.dto.autor.AutorResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.mapper.AutorMapper;
import com.centroweg.biblioteca_jpql.mapper.EditoraMapper;
import com.centroweg.biblioteca_jpql.mapper.LivroMapper;
import com.centroweg.biblioteca_jpql.model.Autor;
import com.centroweg.biblioteca_jpql.model.Livro;
import com.centroweg.biblioteca_jpql.repository.AutorRepository;
import com.centroweg.biblioteca_jpql.repository.EditoraRepository;
import com.centroweg.biblioteca_jpql.repository.LivroRepository;

public class LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;
    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;
    private final EditoraRepository editoraRepository;


    public LivroService(LivroRepository repository, LivroMapper mapper, AutorRepository autorRepository, AutorMapper autorMapper, EditoraRepository editoraRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
        this.editoraRepository = editoraRepository;
    }

    public LivroResponseDTO criarLivro(LivroRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public LivroResponseDTO obterLivroPorId(Long id) {
        Livro livro = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return mapper.toDto(livro);
    }

    public List<LivroResponseDTO> obterTodosLivros() {
        List<Livro> livros = repository.findAll();

        for(Livro livro: livros){
            List<Autor> autores = autorRepository.findAllByLivrosId(livro.getId());
            livro.setAutores(autores);
            livro.setEditora(editoraRepository.findByLivrosId(livro.getId()));
        }

        return livros.stream()
                .map(livro -> mapper.toDto(livro))
                .toList();
    }

    public List<AutorResponseDTO> obterTodosAutoresPorLivroId(Long livroId) {
        List<Autor> autores = autorRepository.findAllByLivrosId(livroId);
        return autores.stream()
        .map(autor -> autorMapper.toDto(autor))
        .toList();
    }
}

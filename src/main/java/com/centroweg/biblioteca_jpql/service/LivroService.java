package com.centroweg.biblioteca_jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.centroweg.biblioteca_jpql.dto.autor.AutorItemResponseDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroRequestDTO;
import com.centroweg.biblioteca_jpql.dto.livro.LivroResponseDTO;
import com.centroweg.biblioteca_jpql.mapper.AutorMapper;
import com.centroweg.biblioteca_jpql.mapper.LivroMapper;
import com.centroweg.biblioteca_jpql.model.Autor;
import com.centroweg.biblioteca_jpql.model.Livro;
import com.centroweg.biblioteca_jpql.repository.AutorRepository;
import com.centroweg.biblioteca_jpql.repository.EditoraRepository;
import com.centroweg.biblioteca_jpql.repository.LivroRepository;

@Service
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

    public List<AutorItemResponseDTO> obterTodosAutoresPorLivroId(Long livroId) {
        List<Autor> autores = autorRepository.findAllByLivrosId(livroId);
        return autores.stream()
        .map(autor -> autorMapper.toItemDto(autor))
        .toList();
    }

    public void deletarLivro(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Livro não encontrado");
        }
        repository.deleteById(id);
    }

    public LivroResponseDTO alocarLivroAEditora(Long livroId, Long editoraId) {
        var livro = repository.findById(livroId)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        var editora = editoraRepository.findById(editoraId)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        livro.setEditora(editora);

        return mapper.toDto(repository.save(livro));
    }

    public LivroResponseDTO alocarLivroAAutor(Long livroId, Long autorId) {
        var livro = repository.findById(livroId)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        var autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livro.getAutores().add(autor);

        return mapper.toDto(repository.save(livro));
    }
}

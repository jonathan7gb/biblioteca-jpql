package com.centroweg.biblioteca_jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.centroweg.biblioteca_jpql.dto.editora.EditoraRequestDTO;
import com.centroweg.biblioteca_jpql.dto.editora.EditoraResponseDTO;
import com.centroweg.biblioteca_jpql.mapper.EditoraMapper;
import com.centroweg.biblioteca_jpql.repository.EditoraRepository;

@Service
public class EditoraService {

    private final EditoraRepository repository;
    private final EditoraMapper mapper;

    public EditoraService(EditoraRepository repository, EditoraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EditoraResponseDTO criarEditora(EditoraRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public EditoraResponseDTO buscarEditoraPorId(Long id) {
        var editora = repository.findById(id).orElseThrow(() -> new RuntimeException("Editora não encontrada"));
        return mapper.toDto(editora);
    }

    public List<EditoraResponseDTO> buscarTodasEditoras() {
        var editoras = repository.findAll();
        return editoras.stream()
                .map(editora -> mapper.toDto(editora))
                .toList();
    }

    public void deletarEditora(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Editora não encontrada");
        }
        repository.deleteById(id);
    }
}

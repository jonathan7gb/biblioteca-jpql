package com.centroweg.biblioteca_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centroweg.biblioteca_jpql.dto.editora.EstatisticasEditoraDTO;
import com.centroweg.biblioteca_jpql.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Editora findByLivrosId(Long livroId);

    @Query("""
        SELECT new com.centroweg.biblioteca_jpql.dto.editora.EstatisticasEditoraDTO(e.nome, COUNT(l))
        FROM Editora e
        JOIN e.livros l
        GROUP BY e.nome
    """)
    List<EstatisticasEditoraDTO> findEstatisticasEditoras();
}

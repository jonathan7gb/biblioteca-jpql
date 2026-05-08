package com.centroweg.biblioteca_jpql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroweg.biblioteca_jpql.model.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Editora findByLivrosId(Long livroId);
}

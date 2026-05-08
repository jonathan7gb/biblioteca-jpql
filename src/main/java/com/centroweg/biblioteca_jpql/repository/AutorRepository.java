package com.centroweg.biblioteca_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroweg.biblioteca_jpql.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    List<Autor> findAllByLivrosId(Long livroId);

}

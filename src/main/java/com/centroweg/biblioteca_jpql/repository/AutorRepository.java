package com.centroweg.biblioteca_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centroweg.biblioteca_jpql.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    List<Autor> findAllByLivrosId(Long livroId);

}

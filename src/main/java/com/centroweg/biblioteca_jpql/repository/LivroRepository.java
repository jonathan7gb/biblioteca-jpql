package com.centroweg.biblioteca_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroweg.biblioteca_jpql.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

    List<Livro> findAllByAutoresId(Long autoresId);
}

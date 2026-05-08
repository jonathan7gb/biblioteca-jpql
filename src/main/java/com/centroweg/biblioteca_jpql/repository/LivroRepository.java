package com.centroweg.biblioteca_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centroweg.biblioteca_jpql.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    List<Livro> findAllByAutoresId(Long autoresId);
}

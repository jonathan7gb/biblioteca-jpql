package com.centroweg.biblioteca_jpql.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centroweg.biblioteca_jpql.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    List<Livro> findByAutoresId(Long autoresId);
    
    List<Livro> findByNome(String nome);

    List<Livro> findByCategoriaAndPrecoLessThan(String categoria, BigDecimal preco);

    List<Livro> findByPrecoBetween(BigDecimal min, BigDecimal max);

    List<Livro> findByIsbnIsNull();
    
    List<Livro> findByCategoriaIn(String categoria);
    
    List<Livro> findByEditoraIdOrderByTituloAsc(Long editoraId);

    long countByAutoresNacionalidade(String nacionalidade);

    @Query("""
        SELECT l.titulo 
        FROM Livro l
        WHERE l.categoria = :categoria
    """)
    List<Livro> findTitulosByCategoria(String categoria);

    @Query("""
        SELECT l 
        FROM Livro l
        JOIN FETCH l.autores a
        WHERE a.nome = :nomeAutor
    """)
    List<Livro> findLivrosByAutorNome(String nomeAutor);
}

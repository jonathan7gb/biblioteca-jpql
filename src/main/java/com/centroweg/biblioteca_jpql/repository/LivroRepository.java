package com.centroweg.biblioteca_jpql.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.centroweg.biblioteca_jpql.model.Livro;
import com.centroweg.biblioteca_jpql.projection.LivroMinimoProjection;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    List<Livro> findByAutoresId(Long autoresId);
    
    List<Livro> findByNome(String nome);

    List<Livro> findByCategoriaAndPrecoLessThan(String categoria, BigDecimal preco);

    List<Livro> findByPrecoBetween(BigDecimal min, BigDecimal max);

    List<Livro> findByIsbnIsNull();
    
    List<Livro> findByCategoriaIn(String categoria);

    List<LivroMinimoProjection> findByCategoria(String categoria);
    
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
        JOIN l.autores a
        WHERE a.nome = :nomeAutor
    """)
    List<Livro> findLivrosByAutorNome(String nomeAutor);

    @Query("""
        SELECT l 
        FROM Livro l 
        JOIN FETCH l.autores 
        WHERE l.id = :id
    """)
    Optional<Livro> findByIdWithAutores(Long id);

    @Query("""
        select avg(l.preco)
        from Livro l
        where l.editora.id = :editoraId
    """)
    Double findMediaPrecoByEditora(Long editoraId);

    @Query("""
        select l
        from Livro l
        where l.preco > (select avg(l2.preco) from Livro l2)
    """)
    List<Livro> findLivrosAcimaDaMediaGeral();

    @Query(value = """
        select id, titulo, isbn, preco, dataPublicacao, categoria
        from livro l
        where EXTRACT(YEAR FROM l.dataPublicacao) = 2023
    """, nativeQuery = true)
    List<Livro> findByDataPublicacao2023();

    @Query(value = """
        select id, titulo, isbn, preco, dataPublicacao, categoria
        from livro l
        join livro_autores la on l.id = la.livro_id
        join autor a on la.autores_id = a.id
        where a.nacionalidade = :nacionalidade
    """, nativeQuery = true)
    List<Livro> findLivrosByNacionalidadeAutorNativo(String nacionalidade);

    @Query(value = """
        select id, titulo, isbn, preco, dataPublicacao, categoria
        from livro l
        where lower(l.categoria) = lower(:categoria)
    """, nativeQuery = true)
    List<Livro> findByCategoriaLower(String categoria);

    @Query(value = """
        SELECT l.titulo AS titulo, l.preco AS preco
        FROM livro l
        WHERE EXTRACT(YEAR FROM l.data_publicacao) = :ano
    """, nativeQuery = true)
    List<LivroMinimoProjection> findLivrosMinimosNativoByAno(@Param("ano") Integer ano);

    <T> List<T> findByTituloContaining(String titulo, Class<T> type);
}

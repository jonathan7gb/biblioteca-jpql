package com.centroweg.biblioteca_jpql.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String isbn;
    private BigDecimal preco;
    private LocalDate dataPublicacao;
    private String categoria;
    
    @ManyToOne
    private Editora editora;

    @ManyToMany
    @JoinTable(name = "livro_autores")
    private List<Autor> autores;

    public Livro(String titulo, String isbn, LocalDate dataPublicacao, BigDecimal preco, String categoria) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.preco = preco;
        this.categoria = categoria;
    }
}


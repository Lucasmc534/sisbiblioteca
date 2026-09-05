package com.lab.jpa.sisbiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

// Entidade JPA que representa um autor e seus livros relacionados.
@Entity
@Table (name = "autores")
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Autor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "autor" , cascade = CascadeType.ALL, fetch = 
    FetchType.LAZY)

    @ToString.Exclude
    private List<Livro> livros = new ArrayList<>();

    public Autor (String nome)
{
    this.nome = nome; 
  }
}



package com.lab.jpa.sisbiblioteca.repository;

import com.lab.jpa.sisbiblioteca.model.Livro; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Centraliza as consultas de livros e herda as operacoes CRUD do Spring Data JPA.
public interface LivroRepository  extends JpaRepository<Livro, Long> {
  List<Livro> findByTituloContainingIgnoreCase(String titulo); 
  List<Livro> findByAutorId(Long autorId);
}

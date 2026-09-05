package com.lab.jpa.sisbiblioteca.repository;

import com.lab.jpa.sisbiblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Centraliza as consultas de autores usando a implementacao gerada pelo Spring Data JPA.
public interface AutorRepository extends JpaRepository<Autor, Long>  {
    List<Autor> findByNomeContainingIgnoreCase(String nome);
}

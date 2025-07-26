package com.alura.literAlura.repository;

import com.alura.literAlura.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento = 0 OR a.anoFalecimento > :ano)")
    List<Autor> buscarAutoresVivosNoAno(int ano);
}

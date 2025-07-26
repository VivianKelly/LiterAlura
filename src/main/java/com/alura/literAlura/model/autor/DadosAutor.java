package com.alura.literAlura.model.autor;

import com.alura.literAlura.model.livro.Livro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonProperty("name") String nome,
        @JsonProperty("birth_year") Integer anoNascimento,
        @JsonProperty("death_year") Integer anoFalecimento,
        @JsonProperty("books") List<Livro> livros) {
}

package com.alura.literAlura.dto;

import com.alura.literAlura.model.livro.DadosLivro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        @JsonProperty("results") List<DadosLivro> livros
) {}


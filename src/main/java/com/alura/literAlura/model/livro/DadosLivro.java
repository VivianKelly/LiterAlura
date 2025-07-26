package com.alura.literAlura.model.livro;

import java.util.List;

import com.alura.literAlura.model.autor.DadosAutor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonProperty("title") String titulo,
        @JsonProperty("authors") List<DadosAutor> autores,
        @JsonProperty("languages") List<String> linguagens,
        @JsonProperty("download_count") int quantidadeDownloads) {
}

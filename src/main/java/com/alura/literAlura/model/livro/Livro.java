package com.alura.literAlura.model.livro;

import com.alura.literAlura.model.autor.Autor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "livros_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))

    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> linguagens = new ArrayList<>();

    private int numeroDownloads;

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.autores = dadosLivro.autores().stream()
                .map(dadosAutor -> new Autor(dadosAutor))
                .collect(Collectors.toList());
        this.linguagens = dadosLivro.linguagens();
        this.numeroDownloads = dadosLivro.quantidadeDownloads();
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(List<String> linguagens) {
        this.linguagens = linguagens;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        String nomesAutores = autores.stream()
                .map(autor -> autor.getNome())
                .collect(Collectors.joining(", "));

        return "--------------------------LIVRO---------------------------\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + nomesAutores + "\n" +
                "Idioma: " + linguagens + "\n" +
                "Número de downloads: " + numeroDownloads + "\n" +
                "----------------------------------------------------------";
    }

}

package com.alura.literAlura.principal;

import com.alura.literAlura.model.autor.Autor;
import com.alura.literAlura.model.autor.DadosAutor;
import com.alura.literAlura.model.livro.DadosLivro;
import com.alura.literAlura.dto.LivroDTO;
import com.alura.literAlura.model.livro.Livro;
import com.alura.literAlura.repository.AutorRepository;
import com.alura.literAlura.repository.LivroRepository;
import com.alura.literAlura.services.ConsumoApi;
import com.alura.literAlura.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private List<DadosLivro> dadosLivros = new ArrayList<>();
    private LivroRepository repositorioLivros;
    private AutorRepository repositorioAutores;

    public Principal(LivroRepository repositorioLivros, AutorRepository repositorioAutores) {
        this.repositorioLivros = repositorioLivros;
        this.repositorioAutores = repositorioAutores;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    Escolha um número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroPorTitulo() {
        DadosLivro dados = getDadosLivro();

        if (dados != null) {
            Livro livro = new Livro(dados);

            List<Autor> autores = dados.autores().stream()
                    .map(dadosAutor -> {
                        Autor autor = new Autor(dadosAutor);
                        autor.getLivros().add(livro);
                        return autor;
                    }).toList();

            livro.setAutores(autores);

            repositorioLivros.save(livro);

            System.out.println(livro);
        }
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var tituloDoLivro = leitura.nextLine().toLowerCase();

        try {
            var json = consumo.obterDados(ENDERECO + tituloDoLivro.replace(" ", "+"));
            LivroDTO resultado = conversor.obterDados(json, LivroDTO.class);

            if (resultado.livros().isEmpty()) {
                System.out.println("Nenhum livro encontrado com esse título. Tente novamente.");
                return null;
            }

            return resultado.livros().get(0);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar o livro: " + e.getMessage());
            return null;
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorioLivros.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorioAutores.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosDeterminadoAno() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        var anoDeBusca = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autoresVivos = repositorioAutores.buscarAutoresVivosNoAno(anoDeBusca);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo nesse ano.");
        } else {
            System.out.println("Autores vivos no ano " + anoDeBusca + ":");
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listarLivrosEmDeterminadoIdioma() {
        System.out.println("Insira o idioma para realizar busca: \n" +
                "es - espanhol \nen - inglês \nfr - francês \npt - português");
        var idiomaSelecionado = leitura.nextLine();

        List<Livro> idioma = repositorioLivros.buscarLivrosPorIdioma(idiomaSelecionado);

        if (idioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
        } else {
            idioma.forEach(System.out::println);
        }

    }
}

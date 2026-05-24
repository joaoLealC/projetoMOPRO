package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

/**
 * Menu inicial que permite carregar dados demo ou de ficheiro.
 */
public class MenuFonteInfo {
    private DB imdb;
    private String opcao;

    public MenuFonteInfo(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              FONTE DA INFORMAÇÃO              #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Carregar dados demo                       #");
            System.out.println("#  2. Carregar de ficheiro                      #");
            System.out.println("#                                               #");
            System.out.println("#  0. Sair                                      #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    imdb = construir();
                    System.out.println("Carregada DB com dados demo");
                    System.out.println(imdb);
                    MenuInicial menuInicial = new MenuInicial(imdb);
                    menuInicial.run();
                    break;

                case "2":
                    imdb = DB.carregarFicheiro("imdb_data.dat");
                    System.out.println(imdb);
                    MenuInicial menuInicialFile = new MenuInicial(imdb);
                    menuInicialFile.run();
                    break;

                case "0":
                    if (imdb != null) {
                        imdb.gravarFicheiro();
                    }
                    System.out.println("A sair... Até à próxima!");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        while (!opcao.equals("0"));
    }

    private static DB construir() {
        DB imdb = new DB("imdb_data.dat");

        // Administradores
        Admin admin = new Admin("admin@example.com", "admin", "admin");
        imdb.adicionarUtilizador(admin);

        // Espetadores
        Espectador ana = criarEspectador("ana@example.com", "ana", "abc", imdb);
        Espectador pedro = criarEspectador("pedro@example.com", "pedro", "qwerty", imdb);

        // Atores
        Ator pierceBrosnan = criarAtor(imdb, "Pierce Brosnan", new Data(1953, 5, 16));
        Ator tomHardy = criarAtor(imdb, "Tom Hardy", new Data(1977, 9, 15));
        Ator helenMirren = criarAtor(imdb, "Helen Mirren", new Data(1945, 7, 26));
        Ator jonathanPrice = criarAtor(imdb, "Jonathan Price", new Data(1947, 6, 1));
        Ator cillianMurphy = criarAtor(imdb, "Cillian Murphy", new Data(1976, 5, 25));

        System.out.println("\n--- A criar Filmes e Séries (Dados Demo) ---");

        try {
            // Filme: Inception
            Filme filme1 = new Filme("Inception", 2010, 148);
            filme1.adicionarGenero(Genero.FICCAO);
            filme1.adicionarGenero(Genero.ACAO);
            filme1.adicionarAtor(tomHardy);
            filme1.adicionarAtor(cillianMurphy);
            filme1.adicionarClassificacao(new Classificacao(ana, 9));
            filme1.adicionarClassificacao(new Classificacao(pedro, 10));
            filme1.adicionarComentario(new Comentario(ana, "Brilhante! Um autêntico quebra-cabeças."));
            imdb.adicionarRecurso(filme1);

            // Filme: The Dark Knight
            Filme filme2 = new Filme("The Dark Knight", 2008, 152);
            filme2.adicionarGenero(Genero.ACAO);
            filme2.adicionarGenero(Genero.DRAMA);
            filme2.adicionarAtor(cillianMurphy);
            filme2.adicionarClassificacao(new Classificacao(pedro, 8));
            imdb.adicionarRecurso(filme2);

            // Série: Peaky Blinders
            Serie serie1 = new Serie("Peaky Blinders", 2013);
            serie1.adicionarGenero(Genero.DRAMA);
            serie1.adicionarAtor(cillianMurphy);
            serie1.adicionarAtor(tomHardy);

            Temporada t1 = new Temporada(1);
            t1.adicionarEpisodio(new Episodio(1, "Episode 1", 55));
            t1.adicionarEpisodio(new Episodio(2, "Episode 2", 58));
            serie1.adicionarTemporada(t1);

            Temporada t2 = new Temporada(2);
            t2.adicionarEpisodio(new Episodio(1, "Episode 1", 59));
            serie1.adicionarTemporada(t2);

            serie1.adicionarClassificacao(new Classificacao(ana, 9));
            imdb.adicionarRecurso(serie1);

            System.out.println("Recursos criados com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro nos dados demo: " + e.getMessage());
        }

        return imdb;
    }

    private static Ator criarAtor(DB imdb, String nome, Data dataNascimento) {
        Ator ator = new Ator(nome, dataNascimento);
        imdb.adicionarAtor(ator);
        System.out.println("Ator '" + nome + "' criado com sucesso");
        return ator;
    }

    private static Espectador criarEspectador(String email, String nome, String password, DB imdb) {
        Espectador espectador = new Espectador(email, nome, password);
        imdb.adicionarUtilizador(espectador);
        System.out.println("Espectador '" + nome + "' criado com sucesso");
        return espectador;
    }
}

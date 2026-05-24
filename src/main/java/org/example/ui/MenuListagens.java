package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;
import java.util.List;

public class MenuListagens {
    private DB imdb;
    private String opcao;

    public MenuListagens(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              LISTAGENS ORDENADAS              #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Filmes ordenados por Título (A-Z)         #");
            System.out.println("#  2. Filmes ordenados por Classificação Média  #");
            System.out.println("#  3. Atores ordenados por Nome (A-Z)           #");
            System.out.println("#  4. Atores ordenados por Número de Filmes     #");
            System.out.println("#  5. Utilizadores com mais Filmes Vistos       #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    exibirFilmes(imdb.listarFilmesPorTitulo(), "TÍTULO (A-Z)");
                    break;
                case "2":
                    exibirFilmes(imdb.listarFilmesPorClassificacaoMedia(), "CLASSIFICAÇÃO MÉDIA");
                    break;
                case "3":
                    exibirAtores(imdb.listarAtoresPorNome(), false);
                    break;
                case "4":
                    exibirAtores(imdb.listarAtoresPorNumeroDeFilmes(), true);
                    break;
                case "5":
                    exibirUtilizadores(imdb.listarUtilizadoresPorFilmesVistos());
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    private void exibirFilmes(List<Filme> filmes, String criterio) {
        System.out.println("\n--- FILMES ORDENADOS POR: " + criterio + " ---");
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme registado no sistema.");
            return;
        }
        for (Filme f : filmes) {
            System.out.printf("- %s (%d) | Nota Média: %.1f estrelas [%s]\n",
                    f.getTitulo(), f.getAno(), f.calcularClassificacaoMedia(), f.getCategoriaClassificacao());
        }
    }

    private void exibirAtores(List<Ator> atores, boolean mostrarContagem) {
        System.out.println("\n--- LISTA DE ATORES ORDENADA ---");
        if (atores.isEmpty()) {
            System.out.println("Nenhum ator registado no sistema.");
            return;
        }
        for (Ator a : atores) {
            if (mostrarContagem) {
                System.out.println("- " + a + " | Filmes no sistema: " + imdb.contarFilmesDoAtor(a));
            } else {
                System.out.println("- " + a);
            }
        }
    }

    private void exibirUtilizadores(List<Espectador> espectadores) {
        System.out.println("\n--- UTILIZADORES COM MAIS FILMES VISTOS ---");
        if (espectadores.isEmpty()) {
            System.out.println("Nenhum espetador registado no sistema.");
            return;
        }
        int posicao = 1;
        for (Espectador e : espectadores) {
            System.out.printf("%dº - %s | Filmes Assistidos: %d\n",
                    posicao++, e.toString(), e.getFilmesVistos().size());
        }
    }
}

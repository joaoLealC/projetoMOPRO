package org.example.ui;

import org.example.model.DB;
import org.example.model.RecursoVisual;
import org.example.utils.Utils;
import java.util.List;

public class MenuSemLogin {
    private DB imdb;
    private String opcao;

    public MenuSemLogin(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                 MENU ANÓNIMO                  #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Listar atores                             #");
            System.out.println("#  2. Listar filmes e séries                    #");
            System.out.println("#  3. Pesquisar filmes/séries por título       #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    System.out.println(imdb.listarAtores());
                    break;
                case "2":
                    System.out.println(imdb.listarRecursos());
                    break;
                case "3":
                    pesquisarRecursos();
                    break;
                case "4":
                    MenuListagens uiListagens = new MenuListagens(imdb);
                    uiListagens.run();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        while (!opcao.equals("0"));
    }

    private void pesquisarRecursos() {
        String termo = Utils.readLineFromConsole("Introduza o título a pesquisar: ");
        List<RecursoVisual> resultados = imdb.pesquisaRecursos(termo);
        System.out.println("\n--- Resultados da Pesquisa ---");
        if (resultados.isEmpty()) {
            System.out.println("Nenhum recurso encontrado.");
        } else {
            for (RecursoVisual rv : resultados) {
                System.out.println("- " + rv);
            }
        }

    }
}

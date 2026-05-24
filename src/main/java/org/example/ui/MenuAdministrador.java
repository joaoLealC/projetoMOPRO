package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;

public class MenuAdministrador {
    private DB imdb;
    private String opcao;

    public MenuAdministrador(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#               MENU ADMINISTRADOR              #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Gerir atores                              #");
            System.out.println("#  2. Gerir recursos (Filmes e Séries)          #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    MenuGerirAtores uiAtores = new MenuGerirAtores(imdb);
                    uiAtores.run();
                    break;
                case "2":
                    MenuGerirRecursos uiRecursos = new MenuGerirRecursos(imdb);
                    uiRecursos.run();
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
}

package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;


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
            System.out.println("#                     MENU                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Listar atores                             #");
            System.out.println("#  2. Listar filmes                             #");
            System.out.println("#  3. Listar séries                             #");
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
                    System.out.println(imdb.listarFilmes());
                    Utils.readLineFromConsole("Prima Enter para continuar...");
                    break;
                case "3":
                    System.out.println(imdb.listarSeries());
                    Utils.readLineFromConsole("Prima Enter para continuar...");
                    break;
            }
        }
        while (!opcao.equals("0"));
    }
}



package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;

public class MenuGerirFilmes {
    private DB imbd;

    public MenuGerirFilmes(DB imbd) {
        this.imbd = imbd;
    }

    public void run() {
        System.out.println("\n--- Gestão de Filmes ---");
        System.out.println("Funcionalidade em desenvolvimento...");
        Utils.readLineFromConsole("Prima Enter para voltar...");
    }
}

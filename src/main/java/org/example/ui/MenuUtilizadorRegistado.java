package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuUtilizadorRegistado {
    private DB imdb;
    private Espectador utilizador;
    private Scanner scanner;

    public MenuUtilizadorRegistado(DB imdb, Espectador utilizador) {
        this.utilizador = utilizador;
        this.imdb = imdb;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int opcao;
        do {
            System.out.println("\n#################################################");
            System.out.println("#         MENU - UTILIZADOR REGISTADO           #");
            System.out.println("#################################################");
            System.out.println("#  Olá, " + utilizador.getNome());
            System.out.println("#-----------------------------------------------#");
            System.out.println("#  1. Pesquisar e Ver Detalhes de Filme/Série  #");
            System.out.println("#  2. Marcar Conteúdo como Visto                #");
            System.out.println("#  3. Classificar Conteúdo (1 a 10)             #");
            System.out.println("#  4. Deixar Comentário                         #");
            System.out.println("#  5. Consultar Listagens de Filmes Ordenados   #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar (Logout)                           #");
            System.out.println("#################################################");

            // Usando o método seguro que criámos na Utils
            opcao = Utils.lerInteiroNoIntervalo(scanner, "Escolha uma opção: ", 0, 5);

            switch (opcao) {
                case 1:
                    menuPesquisarRecurso();
                    break;
                case 2:
                    menuMarcarComoVisto();
                    break;
                case 3:
                    menuClassificarRecurso();
                    break;
                case 4:
                    menuAdicionarComentario();
                    break;
                case 5:
                    menuListagensOrdenadas();
                    break;
                case 0:
                    System.out.println("Sessão terminada. Até à próxima!");
                    break;
            }
        } while (opcao != 0);
    }

    // --- MÉTODOS AUXILIARES PARA CADA FUNCIONALIDADE DA UI ---

    private void menuPesquisarRecurso() {
        System.out.println("\n--- PESQUISAR FILME / SÉRIE ---");
        String termo = Utils.lerStringSegura(scanner, "Introduza o título a pesquisar: ");

        ArrayList<Filme> filmes = imdb.pesquisarFilmesPorTitulo(termo);
        ArrayList<Serie> series = imdb.pesquisarSeriesPorTitulo(termo);

        System.out.println("\n--- Filmes Encontrados ---");
        for (Filme f : filmes) {
            System.out.println("- " + f.getTitulo() + " (" + f.getAno() + ") | Duração: " + f.getDuracao() + " min");
        }

        System.out.println("\n--- Séries Encontradas ---");
        for (Serie s : series) {
            System.out.println("- " + s.getTitulo() + " (" + s.getAno() + ")");
        }
    }

    private void menuMarcarComoVisto() {
        System.out.println("\n--- MARCAR COMO VISTO ---");
        String titulo = Utils.lerStringSegura(scanner, "Título do conteúdo: ");
        int ano = Utils.lerInteiroSeguro(scanner, "Ano de lançamento: ");

        // Exemplo procurando primeiro nos filmes (Ajusta à lógica da tua DB)
        Filme f = imdb.getFilme(titulo, ano); // Certifica-te que tens este getter na DB
        if (f != null) {
            f.marcarComoVisto(); // Método da interface MarcavelComoVisto que corrigimos
            System.out.println("✔ Filme '" + f.getTitulo() + "' marcado como visto!");
            return;
        }

        System.out.println("[Aviso] Conteúdo não encontrado no sistema.");
    }

    private void menuClassificarRecurso() {
        System.out.println("\n--- CLASSIFICAR CONTEÚDO ---");
        String titulo = Utils.lerStringSegura(scanner, "Título do conteúdo: ");
        int ano = Utils.lerInteiroSeguro(scanner, "Ano de lançamento: ");

        Filme f = imdb.getFilme(titulo, ano);
        if (f != null) {
            // Regra do enunciado: Validar se já viu antes de classificar
            if (!f.estaVisto()) {
                System.out.println("[Erro] Só pode classificar conteúdos que já marcou como vistos!");
                return;
            }

            int nota = Utils.lerInteiroNoIntervalo(scanner, "Classificação (1 a 10): ", 1, 10);

            try {
                // Supondo que o método adicionarClassificacao lança a vossa 'classificacaoInvalidaExcecao'
                f.adicionarClassificacao(nota);
                System.out.println("✔ Classificação adicionada com sucesso!");
            } catch (classificacaoInvalidaExcecao e) {
                System.out.println("[Erro] " + e.getMessage());
            }
        } else {
            System.out.println("[Aviso] Conteúdo não encontrado.");
        }
    }

    private void menuAdicionarComentario() {
        System.out.println("\n--- DEIXAR COMENTÁRIO ---");
        String titulo = Utils.lerStringSegura(scanner, "Título do conteúdo: ");
        int ano = Utils.lerInteiroSeguro(scanner, "Ano de lançamento: ");

        Filme f = imdb.getFilme(titulo, ano);
        if (f != null) {
            String comentario = Utils.lerStringSegura(scanner, "Escreva o seu comentário: ");
            f.getComentarios().add(this.utilizador.getNome() + ": " + comentario);
            System.out.println("✔ Comentário publicado!");
        } else {
            System.out.println("[Aviso] Conteúdo não encontrado.");
        }
    }

    private void menuListagensOrdenadas() {
        System.out.println("\n--- LISTAGENS DISPONÍVEIS ---");
        System.out.println("1. Filmes por Ordem Alfabética");
        System.out.println("2. Filmes por Classificação Média (Decrescente)");
        int escolha = Utils.lerInteiroNoIntervalo(scanner, "Escolha: ", 1, 2);

        if (escolha == 1) {
            // Chamar o motor de ordenação alfabética da DB
            imdb.listarFilmesPorNome();
        } else {
            // Chamar o motor de ordenação por nota da DB
            imdb.listarFilmesPorClassificacao();
        }
    }
}


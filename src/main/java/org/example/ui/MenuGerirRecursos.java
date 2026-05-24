package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;
import java.util.List;

public class MenuGerirRecursos {
    private DB imdb;
    private String opcao;

    public MenuGerirRecursos(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                 GERIR RECURSOS                #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Ver filmes e séries                       #");
            System.out.println("#  2. Adicionar Filme                           #");
            System.out.println("#  3. Adicionar Série                           #");
            System.out.println("#  4. Remover Filme/Série                       #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    System.out.println(imdb.listarRecursos());
                    break;
                case "2":
                    adicionarFilme();
                    break;
                case "3":
                    adicionarSerie();
                    break;
                case "4":
                    removerRecurso();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarFilme() {
        System.out.println("\n--- Adicionar Novo Filme ---");
        String titulo = Utils.readLineFromConsole("Título: ");
        int ano = Utils.readIntFromConsole("Ano de lançamento: ");
        int duracao = Utils.readIntFromConsole("Duração (minutos): ");

        Filme novoFilme = new Filme(titulo, ano, duracao);

        // Configurar géneros e atores com validações
        if (!configurarRecurso(novoFilme)) return;

        try {
            imdb.adicionarRecurso(novoFilme);
            System.out.println("Filme '" + titulo + "' adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarSerie() {
        System.out.println("\n--- Adicionar Nova Série ---");
        String titulo = Utils.readLineFromConsole("Título: ");
        int ano = Utils.readIntFromConsole("Ano de início: ");

        Serie novaSerie = new Serie(titulo, ano);

        // Configurar géneros e atores com validações
        if (!configurarRecurso(novaSerie)) return;

        int numTemporadas = Utils.readIntFromConsole("Quantas temporadas deseja adicionar? ");
        for (int i = 1; i <= numTemporadas; i++) {
            Temporada temp = new Temporada(i);
            int numEpisodios = Utils.readIntFromConsole("Quantos episódios para a Temporada " + i + "? ");
            for (int j = 1; j <= numEpisodios; j++) {
                String titEp = Utils.readLineFromConsole("Título do Episódio " + j + ": ");
                int durEp = Utils.readIntFromConsole("Duração (minutos): ");
                temp.adicionarEpisodio(new Episodio(j, titEp, durEp));
            }
            novaSerie.adicionarTemporada(temp);
        }

        try {
            imdb.adicionarRecurso(novaSerie);
            System.out.println("Série '" + titulo + "' adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private boolean configurarRecurso(RecursoVisual rv) {
        // Generos
        System.out.println("\n" + Genero.listarGenerosValidos());
        System.out.println("Adicione pelo menos 1 género (0 para terminar):");
        while (true) {
            int escolha = Utils.readIntFromConsole("Número do género: ");
            if (escolha == 0) {
                // Verificar se tem pelo menos 1 género
                if (rv.getGeneros().isEmpty()) {
                    System.out.println("Erro: tem de adicionar pelo menos 1 género.");
                    // Perguntar se quer continuar ou cancelar
                    if (!Utils.confirma("Deseja tentar novamente? (S/N)")) {
                        System.out.println("Operação cancelada.");
                        return false;
                    }
                } else {
                    break; // tem géneros suficientes, sai do loop
                }
            } else {
                try {
                    Genero g = Genero.porIndice(escolha);
                    rv.adicionarGenero(g);
                    System.out.println("Género '" + g + "' adicionado.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Número inválido. Escolha entre 1 e "
                            + Genero.getGenerosValidos().size() + ".");
                }
            }
        }

        // Atores
        System.out.println("\nAdicione pelo menos 1 ator ao elenco (deixe em branco para terminar):");
        while (true) {
            System.out.println(imdb.listarAtores());
            String nomeAtor = Utils.readLineFromConsole("Nome exato do ator (vazio para terminar): ");
            if (nomeAtor == null || nomeAtor.trim().isEmpty()) {
                // Verificar se tem pelo menos 1 ator
                if (rv.getElenco().isEmpty()) {
                    System.out.println("Erro: tem de associar pelo menos 1 ator.");
                    if (!Utils.confirma("Deseja tentar novamente? (S/N)")) {
                        System.out.println("Operação cancelada.");
                        return false;
                    }
                } else {
                    break; // tem atores suficientes
                }
            } else {
                Ator ator = imdb.pesquisaAtor(nomeAtor);
                if (ator != null) {
                    rv.adicionarAtor(ator);
                    System.out.println("Ator '" + ator.getNome() + "' associado!");
                } else {
                    System.out.println("Ator não encontrado. Verifique o nome.");
                }
            }
        }

        return true;
    }

    private void removerRecurso() {
        System.out.println(imdb.listarRecursos());
        String titulo = Utils.readLineFromConsole("Título exato do recurso a remover: ");
        List<RecursoVisual> resultados = imdb.pesquisaRecursos(titulo);

        RecursoVisual alvo = null;
        for (RecursoVisual rv : resultados) {
            if (rv.getTitulo().equalsIgnoreCase(titulo)) {
                alvo = rv;
                break;
            }
        }

        if (alvo != null) {
            if (Utils.confirma("Tem a certeza que pretende remover '" + alvo.getTitulo() + "'? (S/N)")) {
                imdb.removerRecurso(alvo);
                System.out.println("Recurso eliminado com sucesso!");
            }
        } else {
            System.out.println("Recurso não encontrado.");
        }
    }
}

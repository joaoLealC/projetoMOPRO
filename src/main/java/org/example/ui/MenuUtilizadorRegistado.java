package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;
import java.util.List;

public class MenuUtilizadorRegistado {
    private DB imdb;
    private Espectador utilizador;
    private String opcao;

    public MenuUtilizadorRegistado(DB imdb, Espectador utilizador) {
        this.utilizador = utilizador;
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                MENU ESPECTADOR                 #");
            System.out.println("#################################################");
            System.out.println(" Sessão Iniciada: " + utilizador);
            System.out.println("#################################################");
            System.out.println("#  1. Listar filmes e séries                    #");
            System.out.println("#  2. Pesquisar por título ou ator              #");
            System.out.println("#  3. Ver minha Lista Pessoal (Favoritos)       #");
            System.out.println("#  4. Adicionar à Lista Pessoal                 #");
            System.out.println("#  5. Remover da Lista Pessoal                  #");
            System.out.println("#  6. Marcar conteúdo como visto                #");
            System.out.println("#  7. Classificar e Comentar um conteúdo        #");
            System.out.println("#  8. Ver meu Histórico (Vistos)                #");
            System.out.println("#                                               #");
            System.out.println("#  0. Sair (Logout)                             #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    System.out.println(imdb.listarRecursos());
                    break;
                case "2":
                    pesquisarRecursos();
                    executarPesquisaGeral();
                    break;
                case "3":
                    verListaPessoal();
                    break;
                case "4":
                    adicionarListaPessoal();
                    break;
                case "5":
                    removerListaPessoal();
                    break;
                case "6":
                    marcarComoVisto();
                    break;
                case "7":
                    classificarEComentar();
                    break;
                case "8":
                    verHistorico();
                    break;
                case "0":
                    System.out.println("Sessão terminada.");
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
                if (!rv.getComentarios().isEmpty()) {
                    System.out.println("   [Comentários dos Espetadores]:");
                    for (Comentario c : rv.getComentarios()) {
                        System.out.println("     -> " + c.getTexto());
                    }
                }
            }
        }
    }

    private void verListaPessoal() {
        System.out.println("\n=== A Minha Lista Pessoal (Favoritos) ===");
        if (utilizador.getListaPessoal().isEmpty()) {
            System.out.println("(Lista Vazia)");
        } else {
            for (RecursoVisual rv : utilizador.getListaPessoal()) {
                System.out.println("- " + rv);
            }
        }
    }

    private void adicionarListaPessoal() {
        System.out.println(imdb.listarRecursos());
        String titulo = Utils.readLineFromConsole("Título do conteúdo a favoritar: ");
        RecursoVisual rv = selecionarRecursoPorTitulo(titulo);
        if (rv != null) {
            utilizador.adicionarAListaPessoal(rv);
            System.out.println("Adicionado com sucesso!");
        } else {
            System.out.println("Recurso não encontrado.");
        }
    }

    private void removerListaPessoal() {
        verListaPessoal();
        if (utilizador.getListaPessoal().isEmpty()) return;
        String titulo = Utils.readLineFromConsole("Título a remover dos favoritos: ");
        RecursoVisual rv = null;
        for (RecursoVisual r : utilizador.getListaPessoal()) {
            if (r.getTitulo().equalsIgnoreCase(titulo)) {
                rv = r;
                break;
            }
        }
        if (rv != null) {
            utilizador.removerDaListaPessoal(rv);
            System.out.println("Removido dos favoritos.");
        } else {
            System.out.println("Recurso não pertencia à sua lista.");
        }
    }

    private void marcarComoVisto() {
        System.out.println(imdb.listarRecursos());
        String titulo = Utils.readLineFromConsole("Título do conteúdo visto: ");
        RecursoVisual rv = selecionarRecursoPorTitulo(titulo);
        if (rv == null) {
            System.out.println("Recurso não encontrado.");
            return;
        }

        try {
            if (rv instanceof Filme) {
                Filme f = (Filme) rv;
                f.marcarComoVisto(utilizador);
                System.out.println("Filme '" + f.getTitulo() + "' adicionado aos vistos!");
            } else if (rv instanceof Serie) {
                Serie s = (Serie) rv;
                System.out.println("Temporadas disponíveis:");
                for (Temporada t : s.getTemporadas()) {
                    System.out.println("  Temporada " + t.getNumero());
                }
                int nTemp = Utils.readIntFromConsole("Selecione o número da temporada: ");
                Temporada tempAlvo = null;
                for (Temporada t : s.getTemporadas()) {
                    if (t.getNumero() == nTemp) { tempAlvo = t; break; }
                }
                if (tempAlvo == null) { System.out.println("Temporada inválida."); return; }

                System.out.println("Episódios desta temporada:");
                for (Episodio ep : tempAlvo.getEpisodios()) {
                    System.out.println("  " + ep);
                }
                int nEp = Utils.readIntFromConsole("Selecione o número do episódio: ");
                Episodio epAlvo = null;
                for (Episodio ep : tempAlvo.getEpisodios()) {
                    if (ep.getNumero() == nEp) { epAlvo = ep; break; }
                }
                if (epAlvo != null) {
                    epAlvo.marcarComoVisto(utilizador);
                    System.out.println("Episódio marcado como visto!");
                } else {
                    System.out.println("Episódio inválido.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    private void classificarEComentar() {
        System.out.println(imdb.listarRecursos());
        String titulo = Utils.readLineFromConsole("Título do conteúdo a classificar: ");
        RecursoVisual rv = selecionarRecursoPorTitulo(titulo);

        if (rv == null) {
            System.out.println("Recurso não encontrado.");
            return;
        }

        if (rv instanceof Serie) {
            Serie s = (Serie) rv;
            System.out.println("O que pretende classificar?");
            System.out.println("  1. A série completa");
            System.out.println("  2. Um episódio específico");
            String escolha = Utils.readLineFromConsole("Opção: ");

            if (escolha.equals("2")) {
                classificarEpisodio(s);
                return;
            }
        }

        // Classificar filme ou série completa
        int estrelas = Utils.readIntFromConsole("Classificação (1 a 10): ");
        if (estrelas < 1 || estrelas > 10) {
            System.out.println("Classificação fora dos limites permitidos.");
            return;
        }
        String textoComentario = Utils.readLineFromConsole("Escreva a sua crítica (comentário): ");

        try {

            rv.classificar(utilizador, estrelas);
            rv.adicionarComentario(new Comentario(utilizador, textoComentario));
            System.out.println("Avaliação submetida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void classificarEpisodio(Serie s) {
        System.out.println("Temporadas disponíveis:");
        for (Temporada t : s.getTemporadas()) {
            System.out.println("  Temporada " + t.getNumero());
        }
        int nTemp = Utils.readIntFromConsole("Selecione o número da temporada: ");
        Temporada tempAlvo = null;
        for (Temporada t : s.getTemporadas()) {
            if (t.getNumero() == nTemp) { tempAlvo = t; break; }
        }
        if (tempAlvo == null) { System.out.println("Temporada inválida."); return; }

        System.out.println("Episódios:");
        for (Episodio ep : tempAlvo.getEpisodios()) {
            System.out.println("  " + ep);
        }
        int nEp = Utils.readIntFromConsole("Selecione o número do episódio: ");
        Episodio epAlvo = null;
        for (Episodio ep : tempAlvo.getEpisodios()) {
            if (ep.getNumero() == nEp) { epAlvo = ep; break; }
        }
        if (epAlvo == null) { System.out.println("Episódio inválido."); return; }

        int estrelas = Utils.readIntFromConsole("Classificação (1 a 10): ");
        if (estrelas < 1 || estrelas > 10) {
            System.out.println("Classificação fora dos limites permitidos.");
            return;
        }
        String texto = Utils.readLineFromConsole("Escreva a sua crítica: ");

        try {
            // FIX 2, 3, 4: usa o método do Episodio com validações
            epAlvo.adicionarClassificacao(utilizador, estrelas);
            epAlvo.adicionarComentario(new Comentario(utilizador, texto));
            System.out.println("Avaliação do episódio submetida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void verHistorico() {
        System.out.println("\n=== O MEU HISTÓRICO DE VISUALIZAÇÕES ===");
        System.out.println("Filmes Assistidos:");
        if (utilizador.getFilmesVistos().isEmpty()) {
            System.out.println("  (Nenhum filme registado)");
        } else {
            for (Filme f : utilizador.getFilmesVistos()) {
                System.out.println("  - " + f.getTitulo());
            }
        }
        System.out.println("\nEpisódios de Séries Assistidos:");
        if (utilizador.getEpisodiosVistos().isEmpty()) {
            System.out.println("  (Nenhum episódio registado)");
        } else {
            for (Episodio ep : utilizador.getEpisodiosVistos()) {
                System.out.println("  - " + ep);
            }
        }
    }

    private RecursoVisual selecionarRecursoPorTitulo(String titulo) {
        List<RecursoVisual> resultados = imdb.pesquisaRecursos(titulo);
        for (RecursoVisual rv : resultados) {
            if (rv.getTitulo().equalsIgnoreCase(titulo)) return rv;
        }
        return null;
    }

    private void executarPesquisaGeral() {
        String termo = Utils.readLineFromConsole("Introduza o termo a pesquisar (título ou ator): ").trim();
        if (termo.isEmpty()) { System.out.println("O termo não pode estar vazio."); return; }

        System.out.println("\n[Filmes e Séries Encontrados]:");
        List<RecursoVisual> recursosFiltrados = imdb.pesquisaRecursos(termo);
        if (recursosFiltrados.isEmpty()) {
            System.out.println("   (Nenhum resultado)");
        } else {
            for (RecursoVisual rv : recursosFiltrados) {
                System.out.println("   - " + rv);
                if (rv.getComentarios() != null && !rv.getComentarios().isEmpty()) {
                    for (Comentario c : rv.getComentarios()) {
                        System.out.println("        -> " + c.getTexto());
                    }
                }
            }
        }

        System.out.println("\n[Atores Encontrados]:");
        boolean encontrouAtor = false;
        for (Ator ator : imdb.getLstAtores()) {
            if (ator.toString().toLowerCase().contains(termo.toLowerCase())) {
                encontrouAtor = true;
                System.out.println("   • " + ator);
                for (RecursoVisual rv : imdb.getLstRecursos()) {
                    if (rv.getElenco().contains(ator)) {
                        System.out.println("        -> " + rv.getTitulo() + " (" + rv.getAno() + ")");
                    }
                }
            }
        }
        if (!encontrouAtor) System.out.println("   (Nenhum ator encontrado)");
    }
}
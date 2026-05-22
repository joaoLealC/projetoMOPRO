package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private String url;

    private List<UtilizadorRegistado> lstUtilizadores;
    private List<Ator> lstAtores;
    private List<Filme> lstFilmes;
    private List<Serie> lstSeries;

    public DB(String url) {
        this.url = url;
        this.lstAtores = new ArrayList<>();
        this.lstUtilizadores = new ArrayList<>();
        this.lstFilmes = new ArrayList<>();
        this.lstSeries = new ArrayList<>();
    }

    public void adicionarAtor(Ator a) {
        this.lstAtores.add(a);
    }

    public void adicionarUtilizador(UtilizadorRegistado u) {
        this.lstUtilizadores.add(u);
    }

    public void removerAtor(Ator ator) {
        lstAtores.remove(ator);
    }

    // --- Gestão de Filmes e Séries ---

    public void adicionarFilme(Filme f) {
        if (pesquisaFilme(f.getTitulo(), f.getAno()) == null) {
            this.lstFilmes.add(f);
        } else {
            throw new IllegalArgumentException("O filme já existe na base de dados.");
        }
    }

    public void adicionarSerie(Serie s) {
        if (pesquisaSerie(s.getTitulo(), s.getAno()) == null) {
            this.lstSeries.add(s);
        } else {
            throw new IllegalArgumentException("A série já existe na base de dados.");
        }
    }

    public void removerFilme(Filme f) {
        lstFilmes.remove(f);
    }

    public void removerSerie(Serie s) {
        lstSeries.remove(s);
    }

    // --- Pesquisas ---

    public UtilizadorRegistado pesquisaUtilizador(String username) {
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u.temNome(username)) {
                return u;
            }
        }
        return null;
    }

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) {
                return a;
            }
        }
        return null;
    }

    public Filme pesquisaFilme(String titulo) {
        for (Filme f : lstFilmes) {
            if (f.correspondePesquisa(titulo)) {
                return f;
            }
        }
        return null;
    }

    public Serie pesquisaSerie(String titulo) {
        for (Serie s : lstSeries) {
            if (s.correspondePesquisa(titulo)) {
                return s;
            }
        }
        return null;
    }

    private Filme pesquisaFilme(String titulo, int ano) {
        for (Filme f : lstFilmes) {
            if (f.getTitulo().equalsIgnoreCase(titulo) && f.getAno() == ano) {
                return f;
            }
        }
        return null;
    }

    private Serie pesquisaSerie(String titulo, int ano) {
        for (Serie s : lstSeries) {
            if (s.getTitulo().equalsIgnoreCase(titulo) && s.getAno() == ano) {
                return s;
            }
        }
        return null;
    }

    // --- Autenticação ---

    public UtilizadorRegistado login(String username, String password) {
        UtilizadorRegistado ur = pesquisaUtilizador(username);
        if (ur != null && ur.temPassord(password)) {
            return ur;
        }
        return null;
    }

    // --- Listagens e toString ---

    public String listarUtilizadores() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Utilizadores:");
        if (lstUtilizadores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (UtilizadorRegistado u : lstUtilizadores) {
                sb.append("\n\t- ").append(u).append(u instanceof Admin ? " (admin)" : "");
            }
        }
        return sb.toString();
    }

    public String listarAtores() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Atores:");
        if (lstAtores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Ator ator : lstAtores) {
                sb.append("\n\t- ").append(ator);
            }
        }
        return sb.toString();
    }

    public String listarFilmes() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Filmes:");
        if (lstFilmes.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Filme f : lstFilmes) {
                sb.append("\n\t- ").append(f);
            }
        }
        return sb.toString();
    }

    public String listarSeries() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Séries:");
        if (lstSeries.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Serie s : lstSeries) {
                sb.append("\n\t- ").append(s);
            }
        }
        return sb.toString();
    }

    public Filme getFilme(String titulo, int ano) {
        return pesquisaFilme(titulo, ano); // Reaproveita o teu método private existente!
    }

    // 2. Pesquisa parcial que devolve uma LISTA de filmes encontrados (Requisito do Menu)
    public ArrayList<Filme> pesquisarFilmesPorTitulo(String termo) {
        ArrayList<Filme> resultados = new ArrayList<>();
        for (Filme f : lstFilmes) {
            // Se o título do filme contiver o termo digitado (ignorando maiúsculas/minúsculas)
            if (f.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(f);
            }
        }
        return resultados;
    }

    // 3. Pesquisa parcial que devolve uma LISTA de séries encontradas (Requisito do Menu)
    public ArrayList<Serie> pesquisarSeriesPorTitulo(String termo) {
        ArrayList<Serie> resultados = new ArrayList<>();
        for (Serie s : lstSeries) {
            if (s.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(s);
            }
        }
        return resultados;
    }

    // Devolve a lista ordenada alfabeticamente para o Menu imprimir
    public ArrayList<Filme> listarFilmesPorNome() {
        ArrayList<Filme> copia = new ArrayList<>(lstFilmes);
        // Ordena a cópia por ordem alfabética do título
        copia.sort((f1, f2) -> f1.getTitulo().compareToIgnoreCase(f2.getTitulo()));
        return copia;
    }

    // Devolve a lista ordenada por nota para o Menu imprimir
    public ArrayList<Filme> listarFilmesPorClassificacao() {
        ArrayList<Filme> copia = new ArrayList<>(lstFilmes);
        // Ordena por nota mais alta primeiro (Decrescente) usando o método que adicionaste acima
        copia.sort((f1, f2) -> Double.compare(f2.getClassificacaoMedia(), f1.getClassificacaoMedia()));
        return copia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Estado atual da DB ===\n");
        sb.append("(").append(url).append(")");
        sb.append(listarUtilizadores());
        sb.append(listarAtores());
        sb.append(listarFilmes());
        sb.append(listarSeries());
        return sb.toString();
    }
}
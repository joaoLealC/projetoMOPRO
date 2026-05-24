package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a Base de Dados em memória do sistema.
 * Implementa Serializable para permitir guardar e carregar todo o estado da aplicação.
 */
public class DB implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;
    private List<UtilizadorRegistado> lstUtilizadores;
    private List<Ator> lstAtores;
    private List<RecursoVisual> lstRecursos;

    /**
     * Constrói uma nova DB com o caminho do ficheiro de persistência.
     * @param url caminho do ficheiro de dados
     */
    public DB(String url) {
        this.url = url;
        this.lstAtores = new ArrayList<>();
        this.lstUtilizadores = new ArrayList<>();
        this.lstRecursos = new ArrayList<>();
    }

    // --- Gestão de Atores ---

    public void adicionarAtor(Ator a) {
        this.lstAtores.add(a);
    }

    public void removerAtor(Ator ator) {
        lstAtores.remove(ator);
    }

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) return a;
        }
        return null;
    }

    public List<Ator> getLstAtores() {
        return this.lstAtores;
    }

    // --- Gestão de Utilizadores ---

    public void adicionarUtilizador(UtilizadorRegistado u) {
        this.lstUtilizadores.add(u);
    }

    public UtilizadorRegistado pesquisaUtilizador(String username) {
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u.temNome(username)) return u;
        }
        return null;
    }

    public UtilizadorRegistado login(String username, String password) {
        UtilizadorRegistado ur = pesquisaUtilizador(username);
        if (ur != null && ur.temPassord(password)) return ur;
        return null;
    }

    // --- Gestão de Recursos ---

    /**
     * Adiciona um recurso visual à base de dados.
     * Regra: não podem existir duplicados com o mesmo título e ano.
     * @throws Exception se já existir um recurso com o mesmo título e ano
     */
    public void adicionarRecurso(RecursoVisual r) throws Exception {
        for (RecursoVisual rv : lstRecursos) {
            if (rv.getTitulo().equalsIgnoreCase(r.getTitulo())
                    && rv.getAno() == r.getAno()) {
                throw new Exception("Já existe um recurso com o título '"
                        + r.getTitulo() + "' e ano " + r.getAno() + ".");
            }
        }
        this.lstRecursos.add(r);
    }

    public void removerRecurso(RecursoVisual r) {
        this.lstRecursos.remove(r);
    }

    public List<RecursoVisual> getLstRecursos() {
        return lstRecursos;
    }

    /**
     * Pesquisa recursos cujo título contenha o texto especificado.
     */
    public List<RecursoVisual> pesquisaRecursos(String titulo) {
        List<RecursoVisual> resultados = new ArrayList<>();
        for (RecursoVisual rv : lstRecursos) {
            if (rv.correspondePesquisa(titulo)) resultados.add(rv);
        }
        return resultados;
    }

    // --- Listagens ---

    public String listarUtilizadores() {
        StringBuilder sb = new StringBuilder("\nLista de Utilizadores:");
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
        StringBuilder sb = new StringBuilder("\nLista de Atores:");
        if (lstAtores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Ator ator : lstAtores) {
                sb.append("\n\t- ").append(ator);
            }
        }
        return sb.toString();
    }

    public String listarRecursos() {
        StringBuilder sb = new StringBuilder("\nLista de Filmes e Séries:");
        if (lstRecursos.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (RecursoVisual rv : lstRecursos) {
                sb.append("\n\t- ").append(rv);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Estado atual da DB ===\n");
        sb.append("(").append(url).append(")");
        sb.append(listarUtilizadores());
        sb.append(listarAtores());
        sb.append(listarRecursos());
        return sb.toString();
    }

    // --- Serialização ---

    /**
     * Grava o estado completo da DB num ficheiro binário.
     */
    public void gravarFicheiro() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.url))) {
            oos.writeObject(this);
            System.out.println("Base de dados gravada com sucesso em: " + this.url);
        } catch (IOException e) {
            System.out.println("Erro ao gravar a base de dados: " + e.getMessage());
        }
    }

    /**
     * Carrega o estado da DB a partir de um ficheiro binário.
     */
    public static DB carregarFicheiro(String urlFicheiro) {
        File f = new File(urlFicheiro);
        if (!f.exists()) {
            System.out.println("Ficheiro não encontrado. Nova base de dados criada.");
            return new DB(urlFicheiro);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(urlFicheiro))) {
            DB dbCarregada = (DB) ois.readObject();
            System.out.println("Base de dados carregada de: " + urlFicheiro);
            return dbCarregada;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar ficheiro. Nova DB criada. Detalhe: " + e.getMessage());
            return new DB(urlFicheiro);
        }
    }

    // --- Ordenações ---

    private List<Filme> obterApenasFilmes() {
        List<Filme> filmes = new ArrayList<>();
        for (RecursoVisual rv : lstRecursos) {
            if (rv instanceof Filme) filmes.add((Filme) rv);
        }
        return filmes;
    }

    /** Lista filmes ordenados por título (A-Z). */
    public List<Filme> listarFilmesPorTitulo() {
        List<Filme> filmes = obterApenasFilmes();
        filmes.sort((f1, f2) -> f1.getTitulo().compareToIgnoreCase(f2.getTitulo()));
        return filmes;
    }

    /** Lista filmes ordenados por classificação média (maior para menor). */
    public List<Filme> listarFilmesPorClassificacaoMedia() {
        List<Filme> filmes = obterApenasFilmes();
        filmes.sort((f1, f2) -> Double.compare(f2.calcularClassificacaoMedia(), f1.calcularClassificacaoMedia()));
        return filmes;
    }

    /** Lista atores ordenados por nome (A-Z). */
    public List<Ator> listarAtoresPorNome() {
        List<Ator> atores = new ArrayList<>(lstAtores);
        atores.sort((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome()));
        return atores;
    }

    /** Conta em quantos filmes um ator participa. */
    public int contarFilmesDoAtor(Ator ator) {
        int contador = 0;
        for (Filme f : obterApenasFilmes()) {
            if (f.getElenco().contains(ator)) contador++;
        }
        return contador;
    }

    /** Lista atores ordenados por número de filmes (maior para menor). */
    public List<Ator> listarAtoresPorNumeroDeFilmes() {
        List<Ator> atores = new ArrayList<>(lstAtores);
        atores.sort((a1, a2) -> Integer.compare(contarFilmesDoAtor(a2), contarFilmesDoAtor(a1)));
        return atores;
    }

    /** Lista espectadores ordenados por número de filmes vistos (maior para menor). */
    public List<Espectador> listarUtilizadoresPorFilmesVistos() {
        List<Espectador> espectadores = new ArrayList<>();
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u instanceof Espectador) espectadores.add((Espectador) u);
        }
        espectadores.sort((e1, e2) -> Integer.compare(e2.getFilmesVistos().size(), e1.getFilmesVistos().size()));
        return espectadores;
    }
}
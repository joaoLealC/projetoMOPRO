package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Espectador extends UtilizadorRegistado {

    // ADICIONADO: Histórico pessoal de filmes e séries vistos por ESTE espectador
    private List<Filme> filmesVistos;
    private List<Serie> seriesVistas;

    public Espectador(String email, String nome, String password) {
        super(email, nome, password);
        // Inicializa as listas para evitar NullPointerException
        this.filmesVistos = new ArrayList<>();
        this.seriesVistas = new ArrayList<>();
    }

    // --- GETTERS (ADICIONADOS) ---
    public List<Filme> getFilmesVistos() {
        return this.filmesVistos;
    }

    public List<Serie> getSeriesVistas() {
        return this.seriesVistas;
    }

    // --- MÉTODOS DE HISTÓRICO (ADICIONADOS) ---

    /**
     * Adiciona um filme ao histórico do espectador e marca o filme como visto.
     * @param f O filme a ser adicionado.
     */
    public void registarFilmeVisto(Filme f) {
        if (f != null && !filmesVistos.contains(f)) {
            this.filmesVistos.add(f);
            f.marcarComoVisto(); // Ativa a flag no próprio filme se necessário
        }
    }

    /**
     * Adiciona uma série ao histórico do espectador.
     * @param s A série a ser adicionada.
     */
    public void registarSerieVista(Serie s) {
        if (s != null && !seriesVistas.contains(s)) {
            this.seriesVistas.add(s);
        }
    }

    /**
     * Verifica se este espectador já viu um determinado filme.
     */
    public boolean jaViuFilme(Filme f) {
        return registrosContemFilme(f) || (f != null && f.estaVisto());
    }

    private boolean registrosContemFilme(Filme f) {
        return filmesVistos.contains(f);
    }

    @Override
    public String toString() {
        return super.toString() + " | Filmes Vistos: " + CleanListSize(filmesVistos);
    }

    private int CleanListSize(List<?> list) {
        return list == null ? 0 : list.size();
    }
}

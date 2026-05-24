package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma temporada que agrupa múltiplos episódios.
 */
public class Temporada implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private List<Episodio> episodios;

    public Temporada(int numero) {
        this.numero = numero;
        this.episodios = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public List<Episodio> getEpisodios() { return episodios; }

    public void adicionarEpisodio(Episodio ep) {
        this.episodios.add(ep);
    }
}
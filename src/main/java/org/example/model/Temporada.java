package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma temporada de uma série na plataforma.
 * Armazena o número da temporada e a sua respetiva lista de episódios.
 * * @author Grupo ##
 * @version 1.0
 */
public class Temporada implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private ArrayList<Episodio> episodios;

    /**
     * Construtor básico para criar uma temporada.
     * Inicializa a lista de episódios vazia.
     * * @param numero O número da temporada.
     */
    public Temporada(int numero) {
        this.numero = numero;
        this.episodios = new ArrayList<>();
    }

    /**
     * Obtém o número da temporada.
     * * @return O número da temporada.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtém a lista de episódios pertencentes a esta temporada.
     * * @return O ArrayList com os episódios.
     */
    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    /**
     * Adiciona um episódio de forma direta à lista desta temporada.
     * * @param episodio O objeto do episódio a associar.
     */
    public void adicionarEpisodio(Episodio episodio) {
        this.episodios.add(episodio);
    }

    @Override
    public String toString() {
        return "Temporada " + numero + " (" + episodios.size() + " episódios)";
    }
}

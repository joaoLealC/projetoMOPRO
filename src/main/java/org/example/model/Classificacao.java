package org.example.model;

import java.io.Serializable;

/**
 * Representa uma classificação em estrelas dada por um espetador.
 */
public class Classificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Espectador autor;
    private int estrelas; // Valor de 1 a 10

    public Classificacao(Espectador autor, int estrelas) {
        this.autor = autor;
        this.estrelas = estrelas;
    }

    public Espectador getAutor() { return autor; }
    public int getEstrelas() { return estrelas; }
}

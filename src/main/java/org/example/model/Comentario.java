package org.example.model;

import java.io.Serializable;

/**
 * Representa um comentário em texto escrito por um espetador.
 */
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    private Espectador autor;
    private String texto;

    public Comentario(Espectador autor, String texto) {
        this.autor = autor;
        this.texto = texto;
    }

    public Espectador getAutor() { return autor; }
    public String getTexto() { return texto; }
}

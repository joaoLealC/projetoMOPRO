package org.example.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Recurso implements Pesquisavel {
    protected String titulo;
    protected int ano;
    protected List<String> generos;

    public Recurso(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
        this.generos = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public void adicionarGenero(String genero) {
        if (!generos.contains(genero)) {
            generos.add(genero);
        }
    }

    // Resolve o erro da interface Pesquisavel
    @Override
    public boolean correspondePesquisa(String pesquisa) {
        return this.titulo.equalsIgnoreCase(pesquisa);
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ")";
    }
}
package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Filme extends Recurso implements MarcavelComoVisto {
    private int duracaoMinutos;
    private List<Ator> atores;
    private boolean visto;

    public Filme(String titulo, int ano, int duracaoMinutos) {
        super(titulo, ano);
        this.duracaoMinutos = duracaoMinutos;
        this.atores = new ArrayList<>();
        this.visto = false;
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    // Resolve os erros da interface MarcavelComoVisto
    @Override
    public void marcarComoVisto() {
        this.visto = true;
    }

    @Override
    public boolean estaVisto() {
        return visto;
    }

    @Override
    public String toString() {
        return "Filme: " + super.toString() + " - " + duracaoMinutos + " min | Visto: " + (visto ? "Sim" : "Não");
    }
}
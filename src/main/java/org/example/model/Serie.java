package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Recurso {
    private List<Object> temporadas; // Depois podes mudar para List<Temporada> quando criares a classe

    public Serie(String titulo, int ano) {
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Série: " + super.toString();
    }
}
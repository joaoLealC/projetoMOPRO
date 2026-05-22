package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Recurso {
    private List<Temporada> temporadas;
    private List<Integer> classificacoes = new ArrayList<>();
    private List<String> comentarios = new ArrayList<>();

    public Serie(String titulo, int ano) {
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }
    public void adicionarTemporada(Temporada t) {
        this.temporadas.add(t);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void adicionarClassificacao(int nota) throws classificacaoInvalidaExcecao {
        if (nota < 1 || nota > 10) {
            throw new classificacaoInvalidaExcecao("A classificação deve ser entre 1 e 10!");
        }
        this.classificacoes.add(nota);
    }

    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        int soma = 0;
        for (int nota : classificacoes) {
            soma += nota;
        }
        return (double) soma / classificacoes.size();
    }

    @Override
    public String toString() {
        return "Série: " + super.toString();
    }
}
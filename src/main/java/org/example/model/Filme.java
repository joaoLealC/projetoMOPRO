package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Filme extends Recurso implements MarcavelComoVisto {
    private final int duracaoMinutos;
    private List<Ator> atores;
    private boolean visto;

    // --- APENAS ADICIONAMOS ESTAS DUAS LISTAS QUE FALTAVAM ---
    private List<Integer> classificacoes = new ArrayList<>();
    private List<String> comentarios = new ArrayList<>();

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



    // --- APENAS ACRESCENTAMOS ESTES MÉTODOS NO FIM PARA O MENU UTILIZAR ---

    public int getDuracao() {
        return this.duracaoMinutos;
    }

    public List<Ator> getAtores() {
        return this.atores;
    }

    public List<String> getComentarios() {
        return this.comentarios;
    }

    public List<Integer> getClassificacoes() {
        return this.classificacoes;
    }

    public void adicionarClassificacao(int nota) throws classificacaoInvalidaExcecao {
        if (nota < 1 || nota > 10) {
            throw new classificacaoInvalidaExcecao("A classificação deve ser entre 1 e 10!");
        }
        this.classificacoes.add(nota);
    }
    // Método obrigatório para a listagem ordenada funcionar
    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        int soma = 0;
        for (int nota : classificacoes) {
            soma += nota;
        }
        return (double) soma / classificacoes.size();
    }
    // O teu método original continua aqui intocado:
    @Override
    public String toString() {
        return "Filme: " + super.toString() + " - " + duracaoMinutos + " min | Visto: " + (visto ? "Sim" : "Não");
    }
}
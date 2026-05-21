package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Episodio implements MarcavelComoVisto, Serializable {

    private static final long serialVersionUID = 1L;

    private int numero;
    private String titulo;
    private int duracao; // em minutos
    private boolean visto;
    private ArrayList<Ator> atores; // [cite: 19, 43]
    private ArrayList<Integer> classificacoes; // [cite: 19, 49]
    private ArrayList<String> comentarios; // [cite: 19, 50]

    /**
     * Construtor para criar um Episódio.
     * * @param numero         O número do episódio na temporada.
     * @param titulo         O título do episódio.
     * @param duracao        A duração em minutos.
     * @param atorInicial    O primeiro ator associado (garante a regra de negócio).
     */
    public Episodio(int numero, String titulo, int duracao, Ator atorInicial) {
        this.numero = numero;
        this.titulo = titulo;
        this.duracao = duracao;
        this.visto = false;
        this.atores = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();

        // Garante que o episódio nasce com pelo menos um ator associado
        if (atorInicial != null) {
            this.atores.add(atorInicial);
        }
    }

    public int getNumero() { return numero; }
    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
    public ArrayList<Ator> getAtores() { return atores; }
    public ArrayList<Integer> getClassificacoes() { return classificacoes; }
    public ArrayList<String> getComentarios() { return comentarios; }

    /**
     * Associa um novo ator ao episódio.
     * @param ator O ator a adicionar.
     */
    public void adicionarAtor(Ator ator) {
        if (ator != null && !atores.contains(ator)) {
            atores.add(ator);
        }
    }

    /**
     * Adiciona uma classificação ao episódio (entre 1 e 10).
     * @param estrelas Nota dada de 1 a 10.
     */
    public void adicionarClassificacao(int estrelas) {
        if (estrelas >= 1 && estrelas <= 10) { //
            classificacoes.add(estrelas);
        }
    }

    /**
     * Adiciona um comentário ao episódio.
     * @param comentario O texto do comentário.
     */
    public void adicionarComentario(String comentario) {
        if (comentario != null && !comentario.trim().isEmpty()) {
            comentarios.add(comentario);
        }
    }

    // --- Implementação da Interface MarcavelComoVisto ---

    @Override
    public void marcarComoVisto() {
        this.visto = true; // [cite: 47]
    }

    @Override
    public boolean estaVisto() {
        return this.visto;
    }

    @Override
    public String toString() {
        return "Episódio " + numero + ": " + titulo + " (" + duracao + " min)";
    }
}

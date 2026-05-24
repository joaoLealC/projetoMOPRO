package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base abstrata para Filmes e Séries.
 */
public abstract class RecursoVisual implements Pesquisavel, Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private int ano;
    private List<Genero> generos;
    private List<Ator> elenco;
    private List<Classificacao> classificacoes;
    private List<Comentario> comentarios;

    public RecursoVisual(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
        this.generos = new ArrayList<>();
        this.elenco = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public String getTitulo() { return titulo; }
    public int getAno() { return ano; }
    public List<Genero> getGeneros() { return generos; }
    public List<Ator> getElenco() { return elenco; }
    public List<Classificacao> getClassificacoes() { return classificacoes; }
    public List<Comentario> getComentarios() { return comentarios; }

    public void adicionarGenero(Genero g) {
        if (!generos.contains(g)) generos.add(g);
    }

    public void adicionarAtor(Ator a) {
        if (!elenco.contains(a)) elenco.add(a);
    }

    /**
     * FIX 3 + 4: Valida se o espectador já viu o conteúdo e se ainda não classificou.
     * Usado internamente pelos dados demo (sem validação).
     */
    public void adicionarClassificacao(Classificacao c) {
        this.classificacoes.add(c);
    }

    /**
     * Método com validações completas para uso pelo utilizador no menu.
     */
    public void classificar(Espectador espectador, int estrelas) throws Exception {
        // FIX 4: Verificar se já viu
        if (!isVistoPorEspectador(espectador)) {
            throw new Exception("Só pode classificar conteúdos que já viu!");
        }
        // FIX 3: Verificar se já classificou
        for (Classificacao c : classificacoes) {
            if (c.getAutor().equals(espectador)) {
                throw new Exception("Já classificou este conteúdo anteriormente!");
            }
        }
        classificacoes.add(new Classificacao(espectador, estrelas));
    }

    /**
     * Verifica se este recurso foi visto pelo espectador.
     * Implementado diferente para Filme e Série.
     */
    public abstract boolean isVistoPorEspectador(Espectador espectador);

    public void adicionarComentario(Comentario c) {
        this.comentarios.add(c);
    }

    public abstract int getDuracao();
    public abstract double calcularClassificacaoMedia();
    public abstract String getCategoriaClassificacao();

    @Override
    public boolean correspondePesquisa(String texto) {
        return this.titulo.toLowerCase().contains(texto.toLowerCase());
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") | Duração: " + getDuracao() + " min | Classificação: " +
                String.format("%.1f", calcularClassificacaoMedia()) + " (" + getCategoriaClassificacao() + ")";
    }
}

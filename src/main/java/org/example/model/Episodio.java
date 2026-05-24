package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um episódio individual de uma temporada.
 */
public class Episodio implements MarcavelComoVisto, Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private String titulo;
    private int duracao;

    // FIX 2: Episódio agora tem as suas próprias classificações e comentários
    private List<Classificacao> classificacoes;
    private List<Comentario> comentarios;

    public Episodio(int numero, String titulo, int duracao) {
        this.numero = numero;
        this.titulo = titulo;
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
    public List<Classificacao> getClassificacoes() { return classificacoes; }
    public List<Comentario> getComentarios() { return comentarios; }

    /**
     * Adiciona uma classificação ao episódio.
     * FIX 3 + 4: Valida se já classificou e se já viu o episódio.
     */
    public void adicionarClassificacao(Espectador espectador, int estrelas) throws Exception {
        // FIX 4: Verificar se já viu
        if (!espectador.getEpisodiosVistos().contains(this)) {
            throw new Exception("Só pode classificar episódios que já viu!");
        }
        // FIX 3: Verificar se já classificou
        for (Classificacao c : classificacoes) {
            if (c.getAutor().equals(espectador)) {
                throw new Exception("Já classificou este episódio anteriormente!");
            }
        }
        classificacoes.add(new Classificacao(espectador, estrelas));
    }

    public void adicionarComentario(Comentario c) {
        this.comentarios.add(c);
    }

    public double calcularClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : classificacoes) soma += c.getEstrelas();
        return soma / classificacoes.size();
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return espectador.getEpisodiosVistos().contains(this);
    }

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (espectador.getEpisodiosVistos().contains(this)) {
            throw new Exception("O espetador já viu este episódio!");
        }
        espectador.getEpisodiosVistos().add(this);
    }

    @Override
    public String toString() {
        return "Episódio " + numero + ": " + titulo + " (" + duracao + " min)";
    }
}
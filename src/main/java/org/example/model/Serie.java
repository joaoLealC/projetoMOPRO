package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class Serie extends RecursoVisual {
    private static final long serialVersionUID = 1L;
    private List<Temporada> temporadas;

    public Serie(String titulo, int ano) {
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }

    public List<Temporada> getTemporadas() { return temporadas; }

    public void adicionarTemporada(Temporada t) {
        this.temporadas.add(t);
    }

    @Override
    public int getDuracao() {
        int total = 0;
        for (Temporada t : temporadas) {
            for (Episodio ep : t.getEpisodios()) {
                total += ep.getDuracao();
            }
        }
        return total;
    }

    @Override
    public double calcularClassificacaoMedia() {
        if (getClassificacoes().isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : getClassificacoes()) soma += c.getEstrelas();
        return soma / getClassificacoes().size();
    }

    @Override
    public String getCategoriaClassificacao() {
        double media = calcularClassificacaoMedia();
        if (getClassificacoes().isEmpty()) return "Sem classificações";
        if (media < 5) return "Fraco";
        if (media <= 7.8) return "Médio";
        return "Bom";
    }

    @Override
    public boolean isVistoPorEspectador(Espectador espectador) {
        for (Temporada t : temporadas) {
            for (Episodio ep : t.getEpisodios()) {
                if (espectador.getEpisodiosVistos().contains(ep)) {
                    return true;
                }
            }
        }
        return false;
    }
}
package org.example.model;


public class Filme extends RecursoVisual implements MarcavelComoVisto {
    private static final long serialVersionUID = 1L;
    private int duracao;

    public Filme(String titulo, int ano, int duracao) {
        super(titulo, ano);
        this.duracao = duracao;
    }

    @Override
    public int getDuracao() {
        return this.duracao;
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
        if (media < 4) return "Fraco";
        if (media <= 8) return "Médio";
        return "Bom";
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return espectador.getFilmesVistos().contains(this);
    }

    @Override
    public boolean isVistoPorEspectador(Espectador espectador) {
        return espectador.getFilmesVistos().contains(this);
    }

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (espectador.getFilmesVistos().contains(this)) {
            throw new Exception("O espetador já viu este filme!");
        }
        espectador.getFilmesVistos().add(this);
    }
}

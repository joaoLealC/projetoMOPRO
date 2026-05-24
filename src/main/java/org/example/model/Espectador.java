package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um utilizador do tipo Espectador na plataforma.
 */
public class Espectador extends UtilizadorRegistado {

    private List<RecursoVisual> listaPessoal;
    private List<Filme> filmesVistos;
    private List<Episodio> episodiosVistos;

    public Espectador(String email, String nome, String password) {
        super(email, nome, password);
        this.listaPessoal = new ArrayList<>();
        this.filmesVistos = new ArrayList<>();
        this.episodiosVistos = new ArrayList<>();
    }

    public List<RecursoVisual> getListaPessoal() { return this.listaPessoal; }
    public List<Filme> getFilmesVistos() { return this.filmesVistos; }
    public List<Episodio> getEpisodiosVistos() { return this.episodiosVistos; }

    public void adicionarFilmeVisto(Filme filme) {
        if (!this.filmesVistos.contains(filme)) this.filmesVistos.add(filme);
    }

    public void adicionarEpisodioVisto(Episodio episodio) {
        if (!this.episodiosVistos.contains(episodio)) this.episodiosVistos.add(episodio);
    }

    public void adicionarAListaPessoal(RecursoVisual recurso) {
        if (!this.listaPessoal.contains(recurso)) this.listaPessoal.add(recurso);
    }

    public void removerDaListaPessoal(RecursoVisual recurso) {
        this.listaPessoal.remove(recurso);
    }

    /**
     * FIX 3: equals necessário para que "c.getAutor().equals(espectador)" funcione
     * corretamente na verificação de classificação duplicada.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Espectador outro = (Espectador) obj;
        return this.temNome(outro.toString().split(" <")[0]);
    }

    @Override
    public String toString() {
        return super.toString() + " [Perfil: Espectador]";
    }
}

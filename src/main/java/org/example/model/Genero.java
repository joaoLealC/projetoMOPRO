package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um género de um recurso visual.
 * Os géneros válidos são definidos pela lista IMDb.
 */
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;

    /** Lista de géneros permitidos (baseada nos géneros IMDb). */
    private static final List<String> GENEROS = new ArrayList<>();

    static {
        GENEROS.add("Ação");
        GENEROS.add("Aventura");
        GENEROS.add("Animação");
        GENEROS.add("Biográfico");
        GENEROS.add("Comédia");
        GENEROS.add("Crime");
        GENEROS.add("Documentário");
        GENEROS.add("Drama");
        GENEROS.add("Fantasia");
        GENEROS.add("Terror");
        GENEROS.add("Musical");
        GENEROS.add("Mistério");
        GENEROS.add("Romance");
        GENEROS.add("Sci-Fi");
        GENEROS.add("Desporto");
        GENEROS.add("Thriller");
        GENEROS.add("Western");
    }

    // Constantes públicas para uso nos dados demo
    public static final Genero ACAO = new Genero("Ação");
    public static final Genero COMEDIA = new Genero("Comédia");
    public static final Genero DRAMA = new Genero("Drama");
    public static final Genero TERROR = new Genero("Terror");
    public static final Genero FICCAO = new Genero("Sci-Fi");

    /**
     * Constrói um Genero com o nome dado.
     * @param nome nome do género (deve ser um dos géneros válidos)
     * @throws IllegalArgumentException se o género não for válido
     */
    public Genero(String nome) {
        if (!isValido(nome)) {
            throw new IllegalArgumentException("Género inválido: " + nome +
                    ". Use listarGenerosValidos() para ver as opções.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Verifica se um nome de género é válido (case-insensitive).
     */
    public static boolean isValido(String nome) {
        for (String g : GENEROS) {
            if (g.equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    /**
     * Devolve a lista de todos os géneros válidos.
     */
    public static List<String> getGenerosValidos() {
        return GENEROS;
    }

    /**
     * Devolve uma string formatada com todos os géneros numerados,
     * para mostrar ao utilizador no menu.
     */
    public static String listarGenerosValidos() {
        StringBuilder sb = new StringBuilder("Géneros disponíveis:\n");
        for (int i = 0; i < GENEROS.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(GENEROS.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Devolve o género correspondente ao índice (1-based).
     * @throws IllegalArgumentException se o índice for inválido
     */
    public static Genero porIndice(int indice) {
        if (indice < 1 || indice > GENEROS.size()) {
            throw new IllegalArgumentException("Índice de género inválido: " + indice);
        }
        return new Genero(GENEROS.get(indice - 1));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Genero outro = (Genero) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}

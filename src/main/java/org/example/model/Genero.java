package org.example.model;

public enum Genero {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    ANIMACAO("Animação"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    TERROR("Terror"),
    ROMANCE("Romance"),
    THRILLER("Thriller");

    /** O nome formatado do género para exibição no ecrã. */
    private final String nomeExibicao;

    /**
     * Construtor interno do enum de Géneros.
     * * @param nomeExibicao O nome legível do género.
     */
    Genero(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    /**
     * Obtém o nome formatado do género.
     * * @return O nome de exibição do género.
     */
    public String getNomeExibicao() {
        return nomeExibicao;
    }

    /**
     * Retorna a representação textual do género.
     * * @return String com o nome do género.
     */
    @Override
    public String toString() {
        return nomeExibicao;
    }
}

package org.example.model;

public class RecursoDuplicadoExcecao extends Exception {
    public RecursoDuplicadoExcecao() {
        super("Erro!");
    }
    public RecursoDuplicadoExcecao(String messagem) {
        super(messagem);
    }

}

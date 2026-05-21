package org.example.model;

// Ao adicionar o "extends Exception", o Java passa a aceitar a tua classe como um erro válido
public class classificacaoInvalidaExcecao extends Exception {

    // Construtor para passares a mensagem de erro personalizada
    public classificacaoInvalidaExcecao(String mensagem) {
        super(mensagem);
    }
}

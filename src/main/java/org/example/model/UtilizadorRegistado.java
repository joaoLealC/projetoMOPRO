package org.example.model;

import java.io.Serializable;


public abstract class UtilizadorRegistado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String nome;
    private String password;


    public UtilizadorRegistado(String email, String nome, String password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public boolean temPassord(String pass) {
        return password.equals(pass);
    }

    public boolean temNome(String username) {
        return username.equalsIgnoreCase(nome);
    }

    @Override
    public String toString() {
        return nome + " <" + email + ">";
    }
}

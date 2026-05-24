package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;

public class Ator implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private Data dataNascimento;

    public Ator(String nome, Data dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public boolean temNome(String nome) {
        return this.nome.equalsIgnoreCase(nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ator outro = (Ator) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " [" + dataNascimento + "]";
    }
}


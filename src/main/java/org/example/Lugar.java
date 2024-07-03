package org.example;

public class Lugar {
    private String nome;
    private int tokens;

    public Lugar(String nome, int tokens) {
        this.nome = nome;
        this.tokens = tokens;
    }

    public void adicionarTokens(int quantidade) {
        this.tokens += quantidade;
    }

    public void removerTokens(int quantidade) {
        this.tokens -= quantidade;
    }

    public boolean temTokensSuficientes(int quantidade) {
        return this.tokens >= quantidade;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + ": " + tokens;
    }
}


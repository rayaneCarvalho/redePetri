package org.example;

public class Lugar {
    private String nome;
    private int fichas;

    public Lugar(String nome, int fichas) {
        this.nome = nome;
        this.fichas = fichas;
    }

    public void adicionarFichas(int quantidade) {
        this.fichas += quantidade;
    }

    public void removerFichas(int quantidade) {
        this.fichas -= quantidade;
    }

    public boolean temFichasSuficientes(int quantidade) {
        return this.fichas >= quantidade;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + ": " + fichas;
    }
}


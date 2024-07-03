package org.example;

public class RedePetri {
    private Lugar[] lugares;
    private Transicao[] transicoes;
    private int[][] matrizEntrada;
    private int[][] matrizSaida;

    public RedePetri(Lugar[] lugares, Transicao[] transicoes, int[][] matrizEntrada, int[][] matrizSaida) {
        this.lugares = lugares;
        this.transicoes = transicoes;
        this.matrizEntrada = matrizEntrada;
        this.matrizSaida = matrizSaida;
    }

    public boolean podeDisparar(int transicaoIndex) {
        for (int i = 0; i < lugares.length; i++) {
            if (matrizEntrada[i][transicaoIndex] > 0 && !lugares[i].temFichasSuficientes(matrizEntrada[i][transicaoIndex])) {
                return false;
            }
        }
        return true;
    }

    public void disparar(int transicaoIndex) {
        if (podeDisparar(transicaoIndex)) {
            for (int i = 0; i < lugares.length; i++) {
                if (matrizEntrada[i][transicaoIndex] > 0) {
                    lugares[i].removerFichas(matrizEntrada[i][transicaoIndex]);
                }
                if (matrizSaida[i][transicaoIndex] > 0) {
                    lugares[i].adicionarFichas(matrizSaida[i][transicaoIndex]);
                }
            }
        }
    }

    public void imprimirEstado() {
        for (Lugar lugar : lugares) {
            System.out.println(lugar);
        }
    }

    public static void main(String[] args) {
        Lugar tanqueCheio = new Lugar("Tanque Cheio", 1);
        Lugar tanqueVazio = new Lugar("Tanque Vazio", 0);
        Lugar processamentoAgua = new Lugar("Processamento de Água", 0);
        Lugar tratamentoBiologico = new Lugar("Tratamento Biológico", 0);
        Lugar tratamentoQuimico = new Lugar("Tratamento Químico", 0);

        Lugar[] lugares = {tanqueCheio, tanqueVazio, processamentoAgua, tratamentoBiologico, tratamentoQuimico};

        Transicao encherTanque = new Transicao("Encher Tanque");
        Transicao esvaziarTanque = new Transicao("Esvaziar Tanque");
        Transicao iniciarTratamentoBiologico = new Transicao("Iniciar Tratamento Biológico");
        Transicao iniciarTratamentoQuimico = new Transicao("Iniciar Tratamento Químico");

        Transicao[] transicoes = {encherTanque, esvaziarTanque, iniciarTratamentoBiologico, iniciarTratamentoQuimico};

        int[][] matrizEntrada = {
                {0, 1, 1, 1}, // tanqueCheio
                {1, 0, 0, 0}, // tanqueVazio
                {0, 0, 0, 0}, // processamentoAgua
                {0, 0, 0, 0}, // tratamentoBiologico
                {0, 0, 0, 0}  // tratamentoQuimico
        };

        int[][] matrizSaida = {
                {1, 0, 0, 0}, // tanqueCheio
                {0, 1, 0, 0}, // tanqueVazio
                {0, 0, 0, 0}, // processamentoAgua
                {0, 0, 1, 0}, // tratamentoBiologico
                {0, 0, 0, 1}  // tratamentoQuimico
        };

        RedePetri rede = new RedePetri(lugares, transicoes, matrizEntrada, matrizSaida);

        System.out.println("Estado Inicial:");
        rede.imprimirEstado();

        rede.disparar(2); // Iniciar Tratamento Biológico
        rede.disparar(3); // Iniciar Tratamento Químico

        System.out.println("\nApós Iniciar Tratamentos Biológico e Químico em Paralelo:");
        rede.imprimirEstado();
    }
}


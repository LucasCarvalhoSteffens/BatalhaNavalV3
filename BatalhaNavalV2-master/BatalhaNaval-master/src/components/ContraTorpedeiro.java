package components;

public class ContraTorpedeiro extends Posicao {
    ContraTorpedeiro(int linhaInicial, int colunaInicial, char direcao) {
        super("ContraTorpedeiro", linhaInicial, colunaInicial, 'C', direcao);
    }

    @Override
    public int getTamanho() {
        return 3;
    }
}
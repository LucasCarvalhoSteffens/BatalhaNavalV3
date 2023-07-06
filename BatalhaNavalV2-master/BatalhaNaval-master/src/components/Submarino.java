package components;

public class Submarino extends Posicao {
    Submarino(int linhaInicial, int colunaInicial, char direcao) {
        super("Submarino", linhaInicial, colunaInicial, 'S', direcao);
    }
    @Override
    public int getTamanho() {
        return 2;
    }
}
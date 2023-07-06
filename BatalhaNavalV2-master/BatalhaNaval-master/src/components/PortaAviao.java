package components;

class PortaAviao extends Posicao {
    PortaAviao(int linhaInicial, int colunaInicial, char direcao) {
        super("PortaAvião", linhaInicial, colunaInicial, 'P', direcao);
    }

    @Override
    public int getTamanho() {
        return 5;
    }
}
package components;

public class NavioTanque extends Posicao {
    NavioTanque(int linhaInicial, int colunaInicial, char direcao) {
        super("NavioTanque", linhaInicial, colunaInicial, 'N', direcao);
    }
    @Override
    public int getTamanho() {
        return 4;
    }
}
package components;

public class Agua extends  Posicao{
    Agua(int linhaInicial, int colunaInicial) {
        super("Agua", linhaInicial, colunaInicial, 'A','V');
    }


    @Override
    public int getTamanho() {
        return 0;
    }
}
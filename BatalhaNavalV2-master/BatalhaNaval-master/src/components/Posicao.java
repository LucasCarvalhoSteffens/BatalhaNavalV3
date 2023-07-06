package components;
public abstract class Posicao {
    protected String tipo;
    protected int linhaInicial;
    protected int colunaInicial;
    protected char caracter;

    protected char direcao;

    Posicao(String tipo, int linhaInicial, int colunaInicial, char caracter, char direcao) {
        this.tipo = tipo;
        this.linhaInicial = linhaInicial;
        this.colunaInicial = colunaInicial;
        this.caracter = caracter;
        this.direcao = direcao;
    }

    public String getTipo() {
        return tipo;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public int getColunaInicial() {
        return colunaInicial;
    }

    public char getCaracter() {
        return caracter;
    }

    public abstract int getTamanho();

    public char getDirecao() {
        return this.direcao;
    }
}
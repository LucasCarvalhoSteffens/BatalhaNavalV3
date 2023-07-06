package components;

public class PosicaoFactory {
    public static Posicao criar(String tipo, int linhaInicial, int colunaInicial, char direcao) {
        if (tipo.equals("PortaAvião")) {
                return new PortaAviao(linhaInicial, colunaInicial, direcao);
        } else if (tipo.equals("NavioTanque")) {
            return new NavioTanque(linhaInicial, colunaInicial, direcao);
        } else if (tipo.equals("ContraTorpedeiro")) {
            return new ContraTorpedeiro(linhaInicial, colunaInicial, direcao);
        } else if (tipo.equals("Submarino")) {
            return new Submarino(linhaInicial, colunaInicial, direcao);
        }

        return null;
    }
}
import components.Posicao;
import components.PosicaoFactory;
import components.Tabuleiro;
import view.Visualizador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getAnonymousLogger();
    private List<Posicao> embarcacoes;  // Lista de objetos Posicao que representam as embarcações
    private Tabuleiro tabuleiro = new Tabuleiro(10);  // Tabuleiro do jogo

    public void posicionarEmbarcacoes() {
        Scanner scanner = new Scanner(System.in);
        embarcacoes = new ArrayList<>();

        String[] tiposEmbarcacoes = {"PortaAvião", "NavioTanque", "ContraTorpedeiro", "Submarino"};
        int[] tamanhosEmbarcacoes = {5, 4, 3, 2};

        for (int i = 0; i < tiposEmbarcacoes.length; i++) {
            String tipo = tiposEmbarcacoes[i];
            int tamanho = tamanhosEmbarcacoes[i];

            System.out.println(tipo + " (" + tamanho + " espaços)");
            System.out.print("Linha inicial (--): ");
            int linhaInicial = scanner.nextInt();
            System.out.print("Coluna inicial (|): ");
            int colunaInicial = scanner.nextInt();
            System.out.print("Direção (V para vertical, H para horizontal): ");
            char direcao = scanner.next().charAt(0);

            Posicao posicao = PosicaoFactory.criar(tipo, linhaInicial, colunaInicial, direcao);  // Cria uma instância de Posicao usando PosicaoFactory

            if (posicaoValida(posicao)) {  // Verifica se a posição da embarcação é válida
                embarcacoes.add(posicao);  // Adiciona a embarcação à lista
                tabuleiro.adicionarEmbarcacao(posicao);  // Adiciona a embarcação ao tabuleiro

                // Exibe o tabuleiro após cada posição de embarcação
                Visualizador visualizador = new Visualizador(tabuleiro);
                visualizador.ver();

                System.out.println(tipo + " posicionado.");
            } else {
                System.out.println("Posição inválida. Já existe uma embarcação ou está fora dos limites do tabuleiro.");
                i--; // Reverter a iteração para permitir nova tentativa na mesma embarcação
            }
        }

        System.out.println("Embarcações posicionadas com sucesso!");
        Visualizador visualizador = new Visualizador(tabuleiro);
        visualizador.ver();
    }

    private boolean posicaoValida(Posicao posicao) {
        int linhaInicial = posicao.getLinhaInicial();
        int colunaInicial = posicao.getColunaInicial();
        char direcao = posicao.getDirecao();
        int tamanho = posicao.getTamanho();

        int linhaFinal = linhaInicial + (direcao == 'H' ? 0 : tamanho - 1);
        int colunaFinal = colunaInicial + (direcao == 'V' ? 0 : tamanho - 1);

        if (linhaFinal >= tabuleiro.getTamanho() || colunaFinal >= tabuleiro.getTamanho()) {
            return false; // Posição fora dos limites do tabuleiro
        }

        for (int linha = linhaInicial; linha <= linhaFinal; linha++) {
            for (int coluna = colunaInicial; coluna <= colunaFinal; coluna++) {
                if (tabuleiro.getMatriz()[linha][coluna] != 0) {
                    return false; // Já existe uma embarcação nesta posição
                }
            }
        }

        return true;
    }

    public boolean verificarAcerto(int linha, int coluna) {
        int valorPosicao = tabuleiro.getMatriz()[linha][coluna];
        return valorPosicao != 0;
    }

    public static void main(String[] args) {
        int port = 12345;
        Main game = new Main();
        GreetServer.startServer(port);  // Inicia o servidor na porta especificada
        game.posicionarEmbarcacoes();  // Posiciona as embarcações no tabuleiro
        // game.visualizar();  // Exibe o tabuleiro no console
    }

    private void visualizar() {
        Visualizador v = new Visualizador(tabuleiro);
        v.ver();
    }
}

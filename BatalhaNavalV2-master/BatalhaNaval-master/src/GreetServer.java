import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GreetServer {
    private static final int BOARD_SIZE = 10;  // Tamanho do tabuleiro do jogo

    public static void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado. Aguardando conexões na porta " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();  // Aguarda a conexão de um cliente
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Inicia uma nova thread para lidar com o cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;  // Socket para a conexão com o cliente
        private final Main game;  // Instância do jogo

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.game = new Main();  // Criação de uma instância da classe Main para o jogo
            this.game.posicionarEmbarcacoes();  // Posiciona as embarcações no tabuleiro do jogo
        }

        @Override
        public void run() {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    Scanner in = new Scanner(clientSocket.getInputStream())
            ) {
                String clientMessage;
                do {
                    clientMessage = in.nextLine();  // Recebe a mensagem do cliente
                    System.out.println("Recebido do cliente: " + clientMessage);

                    // Processa a mensagem do cliente e envia uma resposta
                    String response = processMessage(clientMessage);
                    out.println(response);  // Envia a resposta para o cliente
                } while (!clientMessage.equals("!quit"));

                System.out.println("Cliente desconectado: " + clientSocket.getInetAddress().getHostAddress());
                clientSocket.close();  // Fecha a conexão com o cliente
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processMessage(String message) {
            try {
                String[] coordinates = message.split(" ");  // Divide a mensagem em coordenadas
                int linha = Integer.parseInt(coordinates[0]);  // Obtém a linha da coordenada
                int coluna = Integer.parseInt(coordinates[1]);  // Obtém a coluna da coordenada

                boolean acertou = game.verificarAcerto(linha, coluna);  // Verifica se o tiro acertou uma embarcação
                if (acertou) {
                    return "Acertou!";  // Se acertou, retorna "Acertou!"
                } else {
                    return "Errou!";  // Se errou, retorna "Errou!"
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return "Mensagem inválida. Informe as coordenadas corretamente.";  // Trata erros de formato de mensagem
            }
        }
    }
}

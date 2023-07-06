import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GreetClient {
    private Socket clientSocket;  // Socket para a conexão com o servidor
    private PrintWriter out;  // Responsável pela saída de dados para o servidor
    private BufferedReader in;  // Responsável pela leitura de dados recebidos do servidor

    public void start(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);  // Criação do socket TCP e conexão com o servidor
        out = new PrintWriter(clientSocket.getOutputStream(), true);  // Obtém a stream de saída do socket
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Obtém a stream de entrada do socket
    }

    public void stop() {
        try {
            in.close();  // Fecha a stream de entrada
            out.close();  // Fecha a stream de saída
            clientSocket.close();  // Fecha o socket
        } catch (IOException ex) {
            System.out.println("Erro ao fechar a conexão.");
        }
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);  // Envia a mensagem para o servidor
    }

    public String receiveMessage() throws IOException {
        return in.readLine();  // Lê a mensagem recebida do servidor
    }

    public static void main(String[] args) {
        Main game = new Main();  // Criação de uma instância da classe Main
        GreetClient client = new GreetClient();  // Criação de uma instância do cliente
        game.posicionarEmbarcacoes();  // Chama o método posicionarEmbarcacoes() da classe Main para posicionar embarcações

        try {
            client.start("172.21.96.1", 12345);  // Inicia a conexão com o servidor, passando o endereço IP e a porta

            Scanner scanner = new Scanner(System.in);  // Cria um objeto Scanner para ler a entrada do usuário
            String mensagem;
            String response;
            do {
                System.out.print("Entre uma mensagem (linha coluna): ");
                mensagem = scanner.nextLine();  // Lê a mensagem digitada pelo usuário
                client.sendMessage(mensagem);  // Envia a mensagem para o servidor
                response = client.receiveMessage();  // Recebe a resposta do servidor
                System.out.println("Resposta do servidor: " + response);  // Imprime a resposta do servidor
            } while (!"!quit".equals(mensagem) && !"!quit".equals(response));

            client.sendMessage("!quit");  // Envia uma mensagem "!quit" para o servidor para sinalizar o encerramento da conexão
            System.out.println("Desligando cliente...");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            client.stop();  // Encerra a conexão com o servidor
        }
    }
}

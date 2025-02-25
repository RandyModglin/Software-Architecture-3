package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KWICServer {
    private static final int PORT = 1234;
    private static final KWICLogger logManager = new KWICLogger();

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, logManager)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

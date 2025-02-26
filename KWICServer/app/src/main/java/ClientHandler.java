

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import IO.LineStorage;

public class ClientHandler{
    private final Socket clientSocket;
    private final LineStorage lineStorage;

    public ClientHandler(Socket socket, LineStorage lineStorage) {
        this.clientSocket = socket;
        this.lineStorage = lineStorage;
    }

    public void searchLines() {
        KWICSearcher searcher = (KWICSearcher) OptionReader.getObjectFromStr("KWICSearcher");
        KWICLogger requestLogger = new KWICLogger();
        
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String targetWord = in.readLine();
            
            if (targetWord == null){
                return;
            } 

            ArrayList<String> searchedLines = searcher.SearchFile(lineStorage, targetWord);

            if(searchedLines.size() <= 0){
                requestLogger.logRequest(false);
                return;
            }

            out.print(searchedLines);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

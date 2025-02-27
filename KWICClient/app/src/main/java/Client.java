import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import IO.Output;

public class Client {
    private static final int TIMEOUT_MS = 30000; // 30-second timeout

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        OptionReader.readOptions();
        String outputObjStr = OptionReader.getString("Output");
        Output outputObj = (Output) OptionReader.getObjectFromKey(outputObjStr);

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Enter keywords (or /exit to quit): ");
                String keyword = console.readLine();
                
                if (keyword == null || keyword.equalsIgnoreCase("/exit")) {
                    break;
                }

                try (
                    Socket socket = new Socket(host, port);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    // Set timeout for both connection and response
                    socket.setSoTimeout(TIMEOUT_MS);
                    out.println(keyword);

                    ArrayList<String> receivedLines = new ArrayList<>();
                    try {
                        String response;
                        while ((response = in.readLine()) != null) {
                            receivedLines.add(response);
                        }
                        outputObj.PrintSearch(receivedLines, keyword);
                    } 
                    
                    catch (SocketTimeoutException e) {
                        if (receivedLines.isEmpty()) {
                            System.err.println("The KWIC server is not responding.");
                        } else {
                            outputObj.PrintSearch(receivedLines, keyword);
                            System.err.println("(Partial results - connection timed out)");
                        }
                    }
                } 
                
                catch (IOException e) {
                }
            }
        } catch (IOException e) {
        }
        System.out.println("Client exiting...");
    }
}
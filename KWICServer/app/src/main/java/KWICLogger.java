

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class KWICLogger {
    private AtomicInteger totalRequests = new AtomicInteger(0);
    private AtomicInteger successfulRequests = new AtomicInteger(0);
    private final String logFile = "log.txt";

    public void logRequest(boolean success) {
        int total = totalRequests.incrementAndGet();
        int successful = success ? successfulRequests.incrementAndGet() : successfulRequests.get();

        synchronized (this) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile))) {
                writer.println("Total requests: " + total);
                writer.println("Successful requests: " + successful);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

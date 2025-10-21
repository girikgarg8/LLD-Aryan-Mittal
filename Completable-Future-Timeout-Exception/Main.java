import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        CompletableFuture <String> cf = CompletableFuture.supplyAsync(() -> {
           try {
               Thread.sleep(3000);
               return "Hello World";
           }
           catch (InterruptedException ex) {
               Thread.currentThread().interrupt();
               return "Interrupted";
           }
        });

        /**
         * What happens after timeout:
         *            - If the original future doesn't complete within 2 seconds, this CompletableFuture
         *              will complete exceptionally with a TimeoutException
         *            - The original future continues running in the background despite the timeout
         */

        CompletableFuture <String> withTimeout = cf.orTimeout(2, TimeUnit.SECONDS);
        try {
            System.out.println(withTimeout.join());
        }
        catch (CompletionException ex) {
            System.out.println("Timeout occurred " + ex.getCause());
        }

    }
}
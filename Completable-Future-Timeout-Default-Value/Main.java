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

        /*
         What happens after timeout:
           - If the original future doesn't complete within 2 seconds, this CompletableFuture
        will complete normally with the provided default value
           - The original future continues running in the background despite the timeout
       - If the original future completes before the timeout, its result is used instead
         */

        CompletableFuture<String> withDefault = cf.completeOnTimeout("Default value", 2, TimeUnit.SECONDS);
        System.out.println("With default: " + withDefault.join());

    }
}
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        CompletableFuture <String> future1 = CompletableFuture.supplyAsync(() -> {
           try {
               System.out.println("Future1 running on Thread " + Thread.currentThread().getName());
               Thread.sleep(2000);
               return "Hello";
           }
           catch (InterruptedException ex) {
               Thread.currentThread().interrupt();
               return "Interrupted";
           }
        });

        CompletableFuture <String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Future2 running on Thread " + Thread.currentThread().getName());
                Thread.sleep(2000);
                return "World";
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return "Interrupted";
            }
        });

        // Combine results of two futures
        CompletableFuture <String> combined = future1.thenCombine(future2, (result1, result2) -> {
            return result1 + ", " + result2;
        });

        System.out.println("Combined result " + combined.join());

        // Wait for all futures to complete
        CompletableFuture <Void> all = CompletableFuture.allOf(future1,future2);
        all.thenRun(() -> System.out.println("Both futures completed"));

        // Wait for any one future to complete
        CompletableFuture <Object> any = CompletableFuture.anyOf(future1, future2);
        System.out.println("First completable future completed " + any.join());

    }
}
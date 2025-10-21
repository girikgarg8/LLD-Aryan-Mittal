import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        CompletableFuture.supplyAsync(() -> {
           try {
                Thread.sleep(1000);
           }
           catch (InterruptedException ex) {
               Thread.currentThread().interrupt();
           }
           finally {
               return "Hello from Completable future";
           }
        })
        .thenAccept(result -> {
            System.out.println("Result from previous completable future: " + result);
            System.out.println("Processing after CompletableFuture result");
        });

        System.out.println("Main thread is free to do other tasks while waiting...");

        // To prevent the main thread from exiting immediately,
        // we'll wait for the CompletableFuture to complete.
        try {
            Thread.sleep(2000); // Wait enough time for the async task to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
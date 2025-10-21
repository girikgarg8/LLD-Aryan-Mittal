import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        // Exception in thread "main" java.util.concurrent.CompletionException: java.lang.RuntimeException: This is a test exception
        CompletableFuture <String> completableFuture = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("This is a test exception");
        });

        completableFuture.join();
        // main thread will throw an error and the next line will not be executed
        System.out.println("Main thread continuing");
    }

}
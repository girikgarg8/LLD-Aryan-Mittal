import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        CompletableFuture <String> completableFuture = CompletableFuture.supplyAsync(() -> {
           throw new RuntimeException("This is a test exception");
        });

        try {
            completableFuture.get();
        }
        catch (InterruptedException | ExecutionException ex) {
            System.out.println("Got exception: " + ex.getCause());
        }

        System.out.println("Main thread continuing its tasks");
    }
}
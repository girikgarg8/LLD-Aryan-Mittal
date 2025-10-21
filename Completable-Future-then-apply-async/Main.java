import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void main(String [] args) {
        CompletableFuture <String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("First cf running on thread " + getCurrentThreadName());
            return "Hello World";
        })
        .thenApply(result -> {
            System.out.println("Second cf running on thread " + getCurrentThreadName());
            return result + " ABC";
        })
        .thenApplyAsync(result -> {
            System.out.println("Third cf running on thread " + getCurrentThreadName());
            return result + " DEF";
        });

        System.out.println("Completable future result is " + cf.join());

    }
}
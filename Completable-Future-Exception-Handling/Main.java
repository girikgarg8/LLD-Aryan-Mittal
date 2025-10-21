import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    if (Math.random() > 0.5) {
                        throw new RuntimeException("Something went wrong!");
                    }
                    return "Success";
                })
                // Handle exceptions
                .exceptionally(ex -> {
                    System.out.println("Exception caught: " + ex.getMessage());
                    return "Recovery value";
                });
        System.out.println("Result: " + future.join());

        CompletableFuture <String> handled = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Error occurred");
            }
            return "Success";
        })
         .handle((result, ex) -> {
              if (ex!=null) {
                  return "Handled error " + ex.getMessage();
              }
             return "Handled success: " + result;
        });

        System.out.println("Handled cf result: " + handled.join());

    }
}

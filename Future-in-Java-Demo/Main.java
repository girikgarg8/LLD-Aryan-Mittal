import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String [] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future <String> future = executor.submit(() -> "Hello World");
        System.out.println("Future's result is "+ future.get());

        if (future.isDone()) {
            System.out.println("Task is completed successfully");
        }
        else {
            System.out.println("Task is still pending");
        }

        executor.shutdown();
    }
}
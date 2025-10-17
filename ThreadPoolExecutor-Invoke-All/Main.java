import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Collection <Callable<String>> callables = Arrays.asList(() -> "Task 1", () -> "Task 2");

        List <Future<String>> futures = executor.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println("Result is " + future.get());
        }

        executor.shutdown();
    }
}
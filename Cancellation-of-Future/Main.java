import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String [] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Supports cancellation of tasks that are still in-progress or yet to be started
        Future <?> future = executor.submit(() -> {
            while (true) {
                // long running task
                if (Thread.currentThread().isInterrupted()) break;
            }
        });

        boolean cancelled = future.cancel(true); // interrupt the thread
        System.out.println("Task cancelled: " + cancelled);

        executor.shutdown();
    }
}
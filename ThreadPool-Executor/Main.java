import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        for (int i=0; i<5; i++) {
            final int iteration = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Task with iteration " + iteration + "running on Thread: " + Thread.currentThread().getName());
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Monitoring statistics related to
        System.out.println("Executor active threads: " + executor.getActiveCount());
        System.out.println("Queued tasks: " + executor.getQueue().size());

        Thread.sleep(15000);

        executor.shutdown();
    }
}
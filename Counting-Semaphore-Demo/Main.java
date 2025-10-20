import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.time.Instant;

public class Main {
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0 ; i < 5; i++) {
            executor.submit(() -> {
               try {
                   semaphore.acquire();
                   System.out.println("Thread " + Thread.currentThread().getName() +" is running at timestamp: " + Instant.now());
                   Thread.sleep(3000);
                   semaphore.release();
               }
               catch (InterruptedException ex) {
                   Thread.currentThread().interrupt();
               }
            });
        }

        Thread.sleep(10000);
        executor.shutdown();
    }
}
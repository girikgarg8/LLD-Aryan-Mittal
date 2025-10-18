import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    private static AtomicInteger counter = new AtomicInteger(0);

    private static void increment() {
        counter.incrementAndGet();
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment();
                }
            });
        }

        Thread.sleep(6000);
        System.out.println("Counter is " + counter.get());

        executor.shutdown();
    }
}
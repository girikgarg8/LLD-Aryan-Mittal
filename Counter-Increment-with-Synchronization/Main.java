import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static int counter = 0;

    public synchronized static void increment() {
        counter++;
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0;i<5;i++) {
            executor.execute(() -> {
                for (int j=0;j<100000;j++) increment();
            });
        }

        Thread.sleep(5000);

        System.out.println("Counter is " + counter);

        executor.shutdown();
    }
}
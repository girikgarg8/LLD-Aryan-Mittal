import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static int counter = 0;

    // deliberately not making it synchronized/not using AtomicInteger
    public static void increment() {
        counter++;
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0;i<5;i++) {
            executor.submit(()-> {
                for (int j=0;j<10000;j++) increment();
            });
        }

        System.out.println("Counter is " + counter);

        Thread.sleep(5000);

        executor.shutdown();
    }
}
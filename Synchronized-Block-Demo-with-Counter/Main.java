import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    private static volatile int counter = 0;

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    public static void increment() {
        System.out.println("Non critical section entry: Thread" + getThreadName());

        synchronized (Main.class) {
            System.out.println("Thread " + getThreadName() + " is executing increment");
            counter++;
            System.out.println("Thread " + getThreadName() + " has executed increment");
        }

        System.out.println("Non critical section exit: Thread" + getThreadName());
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0 ; i< 5; i++) {
            executor.submit(() -> increment());
        }

        Thread.sleep(4000);

        executor.shutdown();
    }
}
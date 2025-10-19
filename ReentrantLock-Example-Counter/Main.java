import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {
    private static int count = 0;
    private final static ReentrantLock lock = new ReentrantLock();

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

    private static void increment() {
        lock.lock();
        System.out.println("Thread "+ getThreadName() + " has acquired the lock");
        count++;
        System.out.println("Thread " + getThreadName() + " is about to release the lock");
        lock.unlock();
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> increment());
        }

        Thread.sleep(3000);

        executor.shutdown();
    }
}
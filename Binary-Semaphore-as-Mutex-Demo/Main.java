import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {
    private final static Semaphore mutex = new Semaphore(1);

    public static void execute() throws InterruptedException {
        mutex.acquire();
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing");
        mutex.release();
    }

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i=0; i<3; i++) executor.execute(() -> {
            try {
                execute();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread.sleep(500);
        executor.shutdown();
    }
}
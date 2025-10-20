import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class BarrierSynchronization {
    private final int MAX_THREADS = 5;
    private int count = MAX_THREADS;

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore barrier = new Semaphore(0);

    public void execute() {
        try {
            mutex.acquire();
            count--;
            if (count == 0) {
                System.out.println("Thread " + Thread.currentThread().getName() + " is releasing other threads");
                count = MAX_THREADS;
                barrier.release(MAX_THREADS - 1);
                mutex.release();
            } else {
                mutex.release();
                System.out.println("Thread " + Thread.currentThread().getName() + " is trying to acquire barrier semaphore");
                barrier.acquire();
                System.out.println("Thread " + Thread.currentThread().getName() + " has successfully acquired barrier semaphore");
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

};

public class Main {
    public static void main(String [] args) throws InterruptedException {
        BarrierSynchronization bs = new BarrierSynchronization();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(bs::execute);
        }

        Thread.sleep(3000);
        executor.shutdown();
    }
}
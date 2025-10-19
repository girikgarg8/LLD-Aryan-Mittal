import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {
    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(() -> {
            lock.lock();
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            lock.unlock();
        });

        Thread.sleep(500);

        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            System.out.println("Main thread was succesful in acquiring the lock");
            lock.unlock();
        }
        else {
            System.out.println("Main thread failed to acquire the lock");
        }

        System.out.println("Main thread continuing its execution");

        executor.shutdown();
    }
}
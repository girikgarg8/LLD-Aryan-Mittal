import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
};

public class Main {
    private final static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public static void main(String [] args) throws InterruptedException {
        User user = new User("1", "John");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int iteration = i;

            executor.submit(() -> {
                rwLock.writeLock().lock();
                System.out.println("Writer Thread: " + Thread.currentThread().getName() + " has successfully acquired lock");
                user.setName("Alice " + iteration);
                System.out.println("Writer Thread: "+ Thread.currentThread().getName() + " is about to release lock");
                rwLock.writeLock().unlock();
            });

            executor.submit(() -> {
                rwLock.readLock().lock();
                System.out.println("Reader Thread: " + Thread.currentThread().getName() + " has successfully acquired lock");
                System.out.println("Reader Thread: " + Thread.currentThread().getName() + " has read user name: " + user.getName());
                System.out.println("Reader Thread: "+ Thread.currentThread().getName() + " is about to release lock");
                rwLock.readLock().unlock();
            });
        }

        Thread.sleep(5000);

        executor.shutdown();
    }
}
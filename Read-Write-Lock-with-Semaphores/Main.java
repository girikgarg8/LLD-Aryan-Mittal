import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class ReaderWriter {
    private volatile int readerCount = 0;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore writerLock = new Semaphore(1);

    public void registerReader()  {
        try {
            mutex.acquire();
            readerCount++;
            if (readerCount == 1) writerLock.acquire();
            mutex.release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // Actual business logic of reading
        System.out.println("Reader Thread " + Thread.currentThread().getName() + " is reading");
    }

    public void unregisterReader() {
        try {
            mutex.acquire();
            readerCount--;
            if (readerCount == 0) writerLock.release();
            mutex.release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // Actual business logic of stopping reading
        System.out.println("Reader Thread " + Thread.currentThread().getName() + " has stopped reading");
    }

    public void write() {
        try {
            writerLock.acquire();
            // Actual business logoc of writing
            System.out.println("Writer Thread " + Thread.currentThread().getName() + " is writing");
            writerLock.release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
};

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ReaderWriter readerWriter = new ReaderWriter();

        for (int i = 0; i < 3; i++ ) {
            executor.execute(() -> {
                readerWriter.registerReader();
                readerWriter.unregisterReader();
            });

            executor.execute(() -> {
                readerWriter.write();
            });
        }

        Thread.sleep(5000);
        executor.shutdown();
    }
}
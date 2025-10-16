import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0;i<5;i++) {
            final int taskId = i;

            executor.execute(() -> {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running task ID: " + taskId);
            });
        }

        Thread.sleep(5000);
        executor.shutdown();
    }
}
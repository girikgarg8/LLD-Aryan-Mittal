import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        // Thread Pool Executor with 2 core threads, max 4 threads and queue size of 2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.AbortPolicy());
        // reject tasks if queue and max threads are full
        for (int i=1;i<=10;i++) {
            final int taskId = i;
            executor.execute(() -> {
               System.out.println("Thread " + Thread.currentThread().getName() + " is processing task " + taskId);
               try {
                   Thread.sleep(2000); // simulating task execution
               }
               catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
            });
        }

        Thread.sleep(10000);
        executor.shutdown();
    }
}
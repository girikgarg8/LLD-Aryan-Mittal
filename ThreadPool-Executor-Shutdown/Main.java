import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        // create a fixed size thread pool executor with size 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i=0;i<5;i++) {
            final int taskId = i;

            // submit 5 tasks to the thread pool executor
            executor.submit(() -> {
                try {
                    System.out.println("Thread with Name: " + Thread.currentThread().getName() + " executing task " + taskId);
                    Thread.sleep(6500);
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // initiate shutdown of executor once the tasks have been submitted
        executor.shutdown();

        // check whether executor has been terminated in a given timeout, if not, shut down the executor forcefully
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("Forcing shutdown !");
            }
        }
        catch (InterruptedException ex) {
            executor.shutdownNow();
        }

    }
}
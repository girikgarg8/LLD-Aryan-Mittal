import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        // runnable, initial delay, period, time unit
        executor.scheduleAtFixedRate(() -> System.out.println("Repeats"), 1, 3, TimeUnit.SECONDS);

        Thread.sleep(13000);

        executor.shutdown();
    }
}
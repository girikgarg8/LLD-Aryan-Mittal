import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleWithFixedDelay(() -> System.out.println("Fixed delay task"), 0, 2, TimeUnit.SECONDS);

        Thread.sleep(15000);

        executor.shutdown();
    }
}
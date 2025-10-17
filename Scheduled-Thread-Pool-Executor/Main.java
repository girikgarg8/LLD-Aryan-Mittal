import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        scheduler.schedule(() -> {
            System.out.println("Executed after 3 seconds!");
        }, 3, TimeUnit.SECONDS);

        Thread.sleep(5000);

        scheduler.shutdown();
    }
}
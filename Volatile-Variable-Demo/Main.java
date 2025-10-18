import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static volatile boolean running = true;

    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (running) {

            }
            System.out.println("Thread exited !");
        });

        t1.start();
        Thread.sleep(500);
        running = false;
    }
}
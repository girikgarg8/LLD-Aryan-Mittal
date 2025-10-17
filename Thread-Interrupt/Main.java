import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        Object lock = new Object();

        Runnable runnable = () -> {
            synchronized (lock) {
                try {
                    lock.wait(3000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(runnable);

        t1.start();
        t1.interrupt();
    }
}
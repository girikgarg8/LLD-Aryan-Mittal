import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 is running");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 is running");
        });

        // Set thread priorities
        // Thread priorities in Java are merely hints to JVM, and the OS is not obligated to respect them
        thread1.setPriority(Thread.MIN_PRIORITY); // 1
        thread2.setPriority(Thread.MAX_PRIORITY); // 10

        thread1.start();
        thread2.start();
    }
}
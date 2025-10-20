import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    private static final int NUM_THREADS = 10;
    private final static int NUM_INSERTIONS = 100;

    public static void main(String [] args) {
        Queue <Integer> buffer = new ConcurrentLinkedQueue<>();
        Thread [] threads = new Thread[NUM_THREADS];

        for (int i=0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j=0;j<NUM_INSERTIONS;j++) {
                    buffer.add(j);
                }
            });
            threads[i].start();
        }

        for (Thread thread: threads) {
            try {
                thread.join();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Expected size of queue: "+ NUM_THREADS * NUM_INSERTIONS);
        System.out.println("Actual size of queue: " + buffer.size());
        System.out.println("Queue contents: "+ buffer);
    }
}
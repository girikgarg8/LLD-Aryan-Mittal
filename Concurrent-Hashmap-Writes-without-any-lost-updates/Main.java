import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class Main {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ENTRIES = 100;

    public static void main(String [] args) {
        Map <Integer, String> sharedMap = new ConcurrentHashMap<>();
        Thread [] threads = new Thread[NUM_THREADS];

        for (int i=0; i<NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ENTRIES; j++) {
                    int key = threadId * NUM_ENTRIES + j; // unique key for each entry
                    sharedMap.put(key, "Value " + key); // concurrent writes to the map
                }
            });

            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread: threads) {
            try {
                thread.join();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Expected number of keys in hashmap: " + NUM_THREADS * NUM_ENTRIES);
        System.out.println("Actual number of keys in hashmap: " + sharedMap.size());

        System.out.println("Shared map content: ");
        sharedMap.forEach((key, value) -> System.out.println("Key: " + key + " ==> "+ " Value: "+ value));
    }
}
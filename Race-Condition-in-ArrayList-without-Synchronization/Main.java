import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class Main {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ADDITIONS = 100;

    public static void main(String [] args) {
        List <Integer> numbers = new ArrayList<>();
        Thread [] threads = new Thread[NUM_THREADS];

        for (int i=0; i<NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
               for (int j=0; j<NUM_ADDITIONS; j++) numbers.add(j);
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

        System.out.println("List size is " + numbers.size()); // expected size was 10*100 = 1000 in case all the threads ran without any concurrency issues

        // Print the content of sharedList (may contain corrupted data)
        System.out.println("Shared List: " + numbers);
    }
}
import java.util.*;
import java.util.concurrent.*;

class BoundedBuffer {
    private final Queue <String> buffer = new ConcurrentLinkedQueue<>();
    private final int MAX_BUFFER_SIZE = 5;

    private final Semaphore emptySlots = new Semaphore(MAX_BUFFER_SIZE); // initially, all the slots are empty since buffer is empty
    private final Semaphore fullSlots = new Semaphore(0); // initially, none of the slots are full, since the buffer is empty

    /** Why do we need two different Sempahores - isn't emptySlots+fullSlots = MAX_BUFFER_SIZE at any point of time?
     * Yes, that is true. However, the produce() and consume() methods need to acquire different semaphores at the beginning
     * If we only use one semaphore (emptySlots), how will we acquire a lock on the fullSlots semaphore in consume() method?
     * Hence, we need to maintain both emptySlots and fullSlots semaphores
     */

    public void produce() throws InterruptedException {
        // think - to produce, what do I need - I need atleast one empty slot, so I need to acquire an empty slot
        emptySlots.acquire();
        buffer.add(UUID.randomUUID().toString());
        // increase the number of full slots by one - since we just now produced one item
        fullSlots.release();
    }

    public String consume() throws InterruptedException {
        // think - to consume what do we need - we require atleast one full slot, so we need to acquire a full slot
        fullSlots.acquire();
        String item = buffer.poll();
        // increase the number of empty slots by 1, since we just now consumed one item from buffer
        emptySlots.release();
        return item;
    }
};

public class Main {
    public static void main(String [] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        BoundedBuffer producerConsumer = new BoundedBuffer();

        for (int i = 0; i< 5; i++) {
            executor.submit(() -> {
                try {
                    producerConsumer.consume();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });

            executor.submit(() -> {
                try {
                    producerConsumer.produce();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(5000);
        executor.shutdown();
    }
}
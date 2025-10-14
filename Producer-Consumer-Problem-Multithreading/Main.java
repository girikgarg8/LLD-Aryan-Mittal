import java.util.*;
import java.util.concurrent.*;

class ProducerConsumer {
    private final Queue <String> buffer = new LinkedList<>();
    private final int MAX_BUFFER_SIZE = 10;

    public void produce() throws InterruptedException {
        synchronized(this) {
            while (buffer.size() == MAX_BUFFER_SIZE) {
                System.out.println("Thread " + Thread.currentThread().getName() + " cannot produce since buffer is full");
                wait();
            }
            String item = UUID.randomUUID().toString();
            buffer.add(item);
            System.out.println("Thread " + Thread.currentThread().getName() + " produced " + item);
            notifyAll();
        }
    }

    public void consume() throws InterruptedException {
        synchronized(this) {
            while (buffer.isEmpty()) {
                System.out.println("Thread " + Thread.currentThread().getName() + " cannot consume since buffer is empty");
                wait();
            }
            String item = buffer.remove();
            System.out.println("Thread " + Thread.currentThread().getName() + " consumed " + item);
            notifyAll();
        }
    }
};

public class Main {
    public static void main(String [] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ProducerConsumer producerConsumer = new ProducerConsumer();
        for (int i = 0; i < 5; i++ ) {
            executor.submit(() -> {
                try {
                    producerConsumer.produce();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });

            executor.submit(() -> {
                try {
                    producerConsumer.consume();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
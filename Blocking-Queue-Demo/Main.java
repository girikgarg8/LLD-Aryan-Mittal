import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        BlockingQueue <Integer> queue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                queue.add(1);
                System.out.println("Producer: added 1");
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }).start();

        System.out.println("Consumer: waiting to take an element");
        Integer element = queue.take();
        System.out.println("Consumer: took element: " + element);
    }
}
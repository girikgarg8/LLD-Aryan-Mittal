import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String [] args) {
        ConcurrentLinkedQueue <Integer> queue = new ConcurrentLinkedQueue<>();

        // Non blocking poll: returns immediately with null because queue is empty
        Integer element = queue.poll();
        System.out.println("Polled element: " + element);

        // add an element, this operation is also non-blocking
        queue.add(42);
        System.out.println("Polled element: " + queue.poll());
    }
}
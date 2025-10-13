import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static final Object lock = new Object();

    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("This thread is going to be in waiting state now");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Executing this after the thread has become active after being in waiting state");
            }
        });

        t1.start();
        Thread.sleep(5000);
        synchronized (lock) {
            lock.notifyAll();
        }
        t1.join();
    }
}
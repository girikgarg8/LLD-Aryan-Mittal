import java.util.*;
import java.util.concurrent.*;

class Shared {
    private final Object lock = new Object();

    public void execute() {
        synchronized (lock) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is going to sleep now");
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getName() + " was interrupted");
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " has woken up now");
        }
    }

};

public class Main {
    public static void main(String [] args) throws InterruptedException {
        Shared shared = new Shared();
        Thread t1 = new Thread(shared::execute);
        Thread t2 = new Thread(shared::execute);
        t1.start();
        Thread.sleep(500);
        t2.start();

        t1.join();
        t2.join();
    }
}
import java.util.*;
import java.util.concurrent.*;

class Chef {
  private final Object lock;

  public Chef(final Object lock) {
      this.lock = lock;
  }

  public void produce() throws InterruptedException {
      synchronized (lock) {
          lock.notify(); // notify a waiter to consume the food
          System.out.println("Chef with thread: " + Thread.currentThread().getName() + " has produced food");
      }
  }
};

class Waiter {
    private final Object lock;

    public Waiter(final Object lock) {
        this.lock = lock;
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Waiter with thread: " + Thread.currentThread().getName() + " has entered");
            lock.wait(); // wait until the chef produces the food
            System.out.println("Waiter with thread: " + Thread.currentThread().getName() + " has consumed food");
        }
    }
}

public class Main {
    public static void main(String [] args) {
        Object lock = new Object();
        Chef chef = new Chef(lock);
        Waiter waiter = new Waiter(lock);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0; i<5; i++) {
            executor.submit(() -> {
                try {
                    chef.produce();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });

            executor.submit(() -> {
                try {
                    waiter.consume();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
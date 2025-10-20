import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * CAS is a fundamental atomic operation used in concurrent programming, often in the form of a "read-and-retry" loop.
 *
 * Read: A thread reads the current value of a shared variable from a memory location.
 * Compute: The thread performs a calculation or determines a new value based on the old one.
 * Compare and Swap: The thread then performs the atomic CAS operation, providing three pieces of information to the CPU:
 * The memory location of the variable.
 * The expected value (the value the thread believes the variable currently holds).
 * The new value to be written.
 *
 * Failure: If the value at the memory location does not match the expected value, the CAS fails. The variable is untouched, and the operation signals failure (often by returning a boolean false or the latest value from memory).
 * Retry loop: The thread that failed the CAS will typically enter a loop to try again. It re-reads the new current value, recalculates the new value, and attempts the CAS again. This repeats until the CAS succeeds.
 *
 * Example: Atomic counter
 * To understand why this is necessary, consider a simple atomic counter increment:
 * Thread A reads the counter, sees the value is 10.
 * Thread B reads the counter, also sees the value is 10.
 * Thread B increments the value and successfully performs a CAS, changing the counter to 11.
 * Thread A attempts its CAS. It expects the value to be 10 but the actual value is now 11. The CAS operation fails.
 * Thread A's code enters the retry loop. It re-reads the counter, sees the new value 11, calculates its new value (12), and attempts the CAS again.
 * Assuming no further interference, Thread A's next CAS succeeds, setting the counter to 12.
 */

public class Main {
  public static void main(String [] args) throws InterruptedException {
      AtomicInteger counter = new AtomicInteger(0);

      ExecutorService executor = Executors.newFixedThreadPool(5);

      for (int i=0;i<5;i++) {
          executor.submit(() -> {
             int expectedValue = counter.get();
             int newValue = expectedValue + 1;
             if (counter.compareAndSet(expectedValue, newValue)) {
                 System.out.println(Thread.currentThread().getName() +
                         " successfully updated the counter to " + newValue);
             }
             else {
                 System.out.println(Thread.currentThread().getName() +
                         " failed to update - counter currently at " + counter.get());
             }
          });
      }

      Thread.sleep(2000);
      System.out.println("Value of counter is: "+ counter);
      executor.shutdown();
  }
};
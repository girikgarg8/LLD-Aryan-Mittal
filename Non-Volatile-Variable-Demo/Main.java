import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
    // deliberately not making it volatile
    private static boolean running = true;

    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (running) {

            }
            System.out.println("Thread has exited");
        });

        t1.start();
        Thread.sleep(1000);
        running = false;
    }
}
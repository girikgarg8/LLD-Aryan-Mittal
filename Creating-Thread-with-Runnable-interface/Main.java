import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread "+ Thread.currentThread().getName() + " is printing Hello world");
    }
};

public class Main {
    public static void main(String [] args) {
        System.out.println("Main thread "+ Thread.currentThread().getName() + "is executing");
        Runnable runnable = new MyThread();
        // create a runnable and pass it to thread
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
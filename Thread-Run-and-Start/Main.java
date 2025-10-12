import java.util.*;
import java.util.concurrent.*;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Printing hello world on thread " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String [] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();

        t1.start(); // will spawn a new thread and then execute the run() method on it
        t2.run(); // will execute the run() method on the main thread itself

    }
}
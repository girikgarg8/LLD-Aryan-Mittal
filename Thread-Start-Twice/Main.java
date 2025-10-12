import java.util.*;
import java.util.concurrent.*;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is printing Hello world");
    }
}

public class Main {
    public static void main(String [] args) {
        Thread t1 = new MyThread();

        t1.start();
        t1.start(); // will throw IllegalThreadStateException
    }
}
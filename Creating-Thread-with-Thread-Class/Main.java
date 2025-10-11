import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Printing hello world on Thread " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new MyThread();
        t1.start();
        System.out.println("Main Thread with id " + Thread.currentThread().getName() + " is waiting for the other thread to finish");
        t1.join();
    }
}
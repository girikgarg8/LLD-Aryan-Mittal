import java.util.*;
import java.util.concurrent.*;

class MyThread extends Thread {
    @Override
    public void run() {
        throw new RuntimeException("This is a random exception");
    }
};

public class Main {
    public static void main(String [] args) {
        Thread t1 = new MyThread();
        t1.start();
        System.out.println("Main thread continuing");
    }
}
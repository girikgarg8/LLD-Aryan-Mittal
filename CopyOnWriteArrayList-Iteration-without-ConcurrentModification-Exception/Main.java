import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        List <Integer> nums = new CopyOnWriteArrayList<>();

        nums.add(123);
        nums.add(456);

        Thread t1 = new Thread(() -> {
           for (Integer num: nums) {
               System.out.println("Got number: " + num);
           }
        });

        Thread t2 = new Thread(() -> {
           for (int i = 0; i < 5; i++) nums.add(999);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
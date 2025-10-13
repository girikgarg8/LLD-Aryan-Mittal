import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        System.out.println("Main thread is going to sleep");
        Thread.sleep(3000);
        System.out.println("Main thread has woken up now");
    }
}
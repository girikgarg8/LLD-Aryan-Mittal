import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class MyCustomThread implements Callable <String> {
    @Override
    public String call() {
        System.out.println("Thread " + Thread.currentThread().getId()+ " is running");
        return "Hello";
    }
}

public class Main {
    public static void main(String [] args) throws InterruptedException, ExecutionException { // checked exceptions
        Callable <String> callable = new MyCustomThread();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // create a callable and submit to thread pool executor
        Future <String> f1 = executor.submit(callable);

        // block the main thread until the result of future is achieved
        System.out.println("Future's result is " + f1.get());
        executor.shutdown();
    }
}
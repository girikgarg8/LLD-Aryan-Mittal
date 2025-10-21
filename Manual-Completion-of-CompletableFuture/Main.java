import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        CompletableFuture <String> cf = new CompletableFuture<>();
        cf.complete("Manual result");
        System.out.println("Result of completable future is " + cf.join());
        System.out.println("Completable future done: " + cf.isDone());
    }
}
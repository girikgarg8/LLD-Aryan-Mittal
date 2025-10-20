import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String [] args) {
       List <String> items = new ArrayList<>();

       Thread t1 = new Thread(() -> {
           for (int i=0;i<100;i++) {
               items.add("ABC");
           }
       });

       Thread t2 = new Thread(() -> {
          for (String item: items) {
              System.out.println("Item is "+ item);
          }
       });

       t1.start();
       t2.start();


    }
};
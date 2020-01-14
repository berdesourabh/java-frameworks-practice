package org.ds;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Queue<Integer> intList = new LinkedList<>();

        IntStream.range(0, 100).mapToObj(intList::add);

        Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                try {
                    System.out.println(semaphore.availablePermits() + " " + Thread.currentThread().getName());
                    semaphore.acquire();
                    System.out.println(intList.remove() + " " + Thread.currentThread().getName());
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }


    }
}

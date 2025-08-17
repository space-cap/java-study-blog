package ch13;

// File: AtomicIntegerDemo.java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementAndGet(); // 원자적 증가
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Result: " + counter.get()); // 200000 보장
    }
}

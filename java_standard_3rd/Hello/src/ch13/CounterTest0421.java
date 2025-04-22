package ch13;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterTest0421 {
    private int counter1 = 0;
    private final Object lock = new Object();
    private AtomicInteger counter2 = new AtomicInteger(0);

    // synchronized 블록을 이용한 증가
    public void incrementCounter1() {
        synchronized (lock) {
            counter1++;
        }
    }

    // AtomicInteger를 이용한 증가
    public void incrementCounter2() {
        counter2.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        CounterTest0421 test = new CounterTest0421();
        int threadCount = 100;
        int incrementsPerThread = 100000;

        // synchronized 방식 테스트
        Thread[] threads1 = new Thread[threadCount];
        long startTime1 = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    test.incrementCounter1();
                }
            });
            threads1[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads1[i].join();
        }
        long endTime1 = System.nanoTime();
        long duration1 = endTime1 - startTime1;

        // AtomicInteger 방식 테스트
        Thread[] threads2 = new Thread[threadCount];
        long startTime2 = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    test.incrementCounter2();
                }
            });
            threads2[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads2[i].join();
        }
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;

        System.out.printf("Synchronized increment time (ms): %.3f \n", duration1 / 1_000_000.0);
        System.out.printf("AtomicInteger increment time (ms): %.3f \n", duration2 / 1_000_000.0);
        System.out.println("Final counter1 value: " + test.counter1);
        System.out.println("Final counter2 value: " + test.counter2.get());
    }
}

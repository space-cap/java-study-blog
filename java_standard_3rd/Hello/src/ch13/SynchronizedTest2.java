package ch13;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SynchronizedCounterWithTime {
    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;
    private final Object lock = new Object();

    public synchronized void incrementCounter1() {
        counter1++;
    }

    public void incrementCounter2() {
        synchronized (this) {
            counter2++;
        }
    }

    public void incrementCounter3() {
        synchronized (lock) {
            counter3++;
        }
    }

    public int getCounter1() {
        return counter1;
    }

    public int getCounter2() {
        return counter2;
    }

    public int getCounter3() {
        return counter3;
    }
}

public class SynchronizedTest2 {
    private static final int ITERATIONS = 100000;
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        SynchronizedCounterWithTime counter = new SynchronizedCounterWithTime();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // 1. synchronized 메서드 방식 측정
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    //counter.incrementCounter1();
                }
            });
        }
        shutdownAndAwaitTermination(executor);
        long endTime1 = System.currentTimeMillis();
        System.out.println("[Synchronized Method 방식]");
        System.out.println("Final Counter Value: " + counter.getCounter1());
        System.out.println("Total Execution Time: " + (endTime1 - startTime1) + " milliseconds");

        // 2. synchronized (this) 블록 방식 측정
        counter = new SynchronizedCounterWithTime(); // 카운터 초기화
        executor = Executors.newFixedThreadPool(NUM_THREADS);
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    //counter.incrementCounter2();
                }
            });
        }
        shutdownAndAwaitTermination(executor);
        long endTime2 = System.currentTimeMillis();
        System.out.println("\n[Synchronized (this) 블록 방식]");
        System.out.println("Final Counter Value: " + counter.getCounter2());
        System.out.println("Total Execution Time: " + (endTime2 - startTime2) + " milliseconds");

        // 3. synchronized (lock) 블록 방식 측정
        counter = new SynchronizedCounterWithTime(); // 카운터 초기화
        executor = Executors.newFixedThreadPool(NUM_THREADS);
        long startTime3 = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    //counter.incrementCounter3();
                }
            });
        }
        shutdownAndAwaitTermination(executor);
        long endTime3 = System.currentTimeMillis();
        System.out.println("\n[Synchronized (lock) 블록 방식]");
        System.out.println("Final Counter Value: " + counter.getCounter3());
        System.out.println("Total Execution Time: " + (endTime3 - startTime3) + " milliseconds");
    }

    private static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

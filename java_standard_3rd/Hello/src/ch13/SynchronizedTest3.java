package ch13;

public class SynchronizedTest3 {
    private int counter = 0;

    public synchronized void incrementCounter1() {
        counter++;
    }

    public void incrementCounter2() {
        synchronized (this) {
            counter++;
        }
    }

    private final Object lock = new Object();
    public void incrementCounter3() {
        synchronized (lock) {
            counter++;
        }
    }

    // counter 초기화 메서드
    public void resetCounter() {
        counter = 0;
    }

    public static void main(String[] args) {
        SynchronizedTest3 test = new SynchronizedTest3();
        final int ITERATIONS = 1_000_000;

        // incrementCounter1 시간 측정
        test.resetCounter();
        long start1 = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            test.incrementCounter1();
        }
        long end1 = System.nanoTime();
        System.out.println("incrementCounter1: " + (end1 - start1) / 1_000_000.0 + " ms");

        // incrementCounter2 시간 측정
        test.resetCounter();
        long start2 = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            test.incrementCounter2();
        }
        long end2 = System.nanoTime();
        System.out.println("incrementCounter2: " + (end2 - start2) / 1_000_000.0 + " ms");

        // incrementCounter3 시간 측정
        test.resetCounter();
        long start3 = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            test.incrementCounter3();
        }
        long end3 = System.nanoTime();
        System.out.println("incrementCounter3: " + (end3 - start3) / 1_000_000.0 + " ms");
    }
}

package ch13;

public class SynchronizedTest4 {
    private int counter = 0;
    private final Object lock = new Object();

    public synchronized void incrementCounter1() {
        counter++;
    }

    public void incrementCounter2() {
        synchronized (this) {
            counter++;
        }
    }

    public void incrementCounter3() {
        synchronized (lock) {
            counter++;
        }
    }

    public void resetCounter() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    // 테스트용 Runnable
    private Runnable getTask(int methodType, int iterations) {
        return () -> {
            switch (methodType) {
                case 1:
                    for (int i = 0; i < iterations; i++) incrementCounter1();
                    break;
                case 2:
                    for (int i = 0; i < iterations; i++) incrementCounter2();
                    break;
                case 3:
                    for (int i = 0; i < iterations; i++) incrementCounter3();
                    break;
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 8;        // 사용할 스레드 개수
        final int ITERATIONS = 100_000; // 각 스레드가 수행할 반복 횟수

        SynchronizedTest4 test = new SynchronizedTest4();

        // 1번 메서드 테스트
        test.resetCounter();
        Thread[] threads1 = new Thread[THREADS];
        long start1 = System.nanoTime();
        for (int i = 0; i < THREADS; i++) {
            threads1[i] = new Thread(test.getTask(1, ITERATIONS));
            threads1[i].start();
        }
        for (int i = 0; i < THREADS; i++) threads1[i].join();
        long end1 = System.nanoTime();
        System.out.printf("incrementCounter1: %.3f ms, counter=%d\n",
                (end1 - start1) / 1_000_000.0, test.getCounter());

        // 2번 메서드 테스트
        test.resetCounter();
        Thread[] threads2 = new Thread[THREADS];
        long start2 = System.nanoTime();
        for (int i = 0; i < THREADS; i++) {
            threads2[i] = new Thread(test.getTask(2, ITERATIONS));
            threads2[i].start();
        }
        for (int i = 0; i < THREADS; i++) threads2[i].join();
        long end2 = System.nanoTime();
        System.out.printf("incrementCounter2: %.3f ms, counter=%d\n",
                (end2 - start2) / 1_000_000.0, test.getCounter());

        // 3번 메서드 테스트
        test.resetCounter();
        Thread[] threads3 = new Thread[THREADS];
        long start3 = System.nanoTime();
        for (int i = 0; i < THREADS; i++) {
            threads3[i] = new Thread(test.getTask(3, ITERATIONS));
            threads3[i].start();
        }
        for (int i = 0; i < THREADS; i++) threads3[i].join();
        long end3 = System.nanoTime();
        System.out.printf("incrementCounter3: %.3f ms, counter=%d\n",
                (end3 - start3) / 1_000_000.0, test.getCounter());
    }
}

package ch13;

public class SynchronizedTest1 {
    private int counter = 0;

    public synchronized void incrementCounter() {
        counter++;
        //System.out.println(Thread.currentThread().getName() + " - Counter: " + counter);
    }

    public void incrementCounter2() {
        synchronized (this) {
            counter++;
            //System.out.println(Thread.currentThread().getName() + " - Counter: " + counter);
        }
    }

    private Object lock = new Object();

    public void incrementCounter3() {
        synchronized (lock) {
            counter++;
            //System.out.println(Thread.currentThread().getName() + " - Counter: " + counter);
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        SynchronizedTest1 counter = new SynchronizedTest1();

        // 시작 시간 측정
        long startTime = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementCounter();
            }
        });
        thread1.setName("Thread-1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementCounter();
            }
        });
        thread2.setName("Thread-2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementCounter();
            }
        });
        thread3.setName("Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 종료 시간 측정
        long endTime = System.currentTimeMillis();

        System.out.println("\nFinal Counter Value: " + counter.getCounter());
        System.out.println("Total Execution Time: " + (endTime - startTime) + " milliseconds");
    }
}

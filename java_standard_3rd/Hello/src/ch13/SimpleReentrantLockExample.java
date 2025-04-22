package ch13;

import java.util.concurrent.locks.ReentrantLock;

public class SimpleReentrantLockExample {
    private static int counter = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 2개의 스레드가 동시에 counter를 1000번씩 증가시킴
        Thread t1 = new Thread(() -> incrementCounter());
        Thread t2 = new Thread(() -> incrementCounter());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 counter 값: " + counter);
    }

    private static void incrementCounter() {
        for (int i = 0; i < 2000; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }
}

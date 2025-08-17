package ch13;

// File: ReentrantLockDemo.java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    static class SafeCounter {
        private int value = 0;
        // 공정성 모드(true)는 오래 기다린 쓰레드가 먼저 락을 얻도록 시도 (성능은 약간 저하될 수 있음)
        private final Lock lock = new ReentrantLock(true);

        public void increment() {
            lock.lock();          // 반드시 unlock과 쌍을 이루어야 함
            try {
                value++;
            } finally {
                lock.unlock();    // 예외가 발생해도 락을 반드시 해제
            }
        }

        public int get() {
            lock.lock();
            try {
                return value;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Result: " + counter.get());
    }
}

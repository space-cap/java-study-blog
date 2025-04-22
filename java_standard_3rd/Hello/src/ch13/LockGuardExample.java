package ch13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockGuardExample {

    // AutoCloseable을 구현한 래퍼 클래스
    static class AutoLock implements AutoCloseable {
        private final Lock lock;

        public AutoLock(Lock lock) {
            this.lock = lock;
            this.lock.lock();
        }

        @Override
        public void close() {
            lock.unlock();
        }
    }

    // 공유 자원
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 두 개의 스레드에서 동시에 counter 증가
        Thread t1 = new Thread(LockGuardExample::increment);
        Thread t2 = new Thread(LockGuardExample::increment);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("최종 counter 값: " + counter);
    }

    private static void increment() {
        for (int i = 0; i < 10000; i++) {
            // try-with-resources로 AutoLock 사용
            try (AutoLock al = new AutoLock(lock)) {
                counter++;
            }
        }
    }
}

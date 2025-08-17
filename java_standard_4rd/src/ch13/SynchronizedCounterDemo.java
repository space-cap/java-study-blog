package ch13;

// File: SynchronizedCounterDemo.java
public class SynchronizedCounterDemo {
    static class SafeCounter {
        private int value = 0;

        // 메서드 전체 동기화: 이 메서드에 동시에 한 쓰레드만 진입 가능
        public synchronized void increment() {
            value++; // 이제 원자적으로 보호됨
        }

        // 읽기만 하는 경우도 일관성 유지를 위해 동기화가 필요할 수 있음
        public synchronized int get() {
            return value;
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

        System.out.println("Expected: 200000");
        System.out.println("Actual:   " + counter.get()); // 항상 200000 보장
    }
}


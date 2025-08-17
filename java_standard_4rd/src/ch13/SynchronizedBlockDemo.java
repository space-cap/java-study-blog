package ch13;

// File: SynchronizedBlockDemo.java
public class SynchronizedBlockDemo {
    static class SafeCounter {
        private int value = 0;
        private final Object lock = new Object(); // 전용 락 객체

        public void increment() {
            // 임계 구역은 value 변경 부분으로 한정
            synchronized (lock) {
                value++;
            }
        }

        public int get() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                // 임계 구역 바깥에서 할 일이 있다면 그 부분은 동기화 없이 수행 가능
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Result: " + counter.get());
    }
}


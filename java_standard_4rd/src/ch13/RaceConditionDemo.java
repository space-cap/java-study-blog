package ch13;

// File: RaceConditionDemo.java
public class RaceConditionDemo {
    static class UnsafeCounter {
        private int value = 0;

        // 동기화가 없는 증가 메서드 (race condition 발생 가능)
        public void increment() {
            // value++ 는 사실상 3단계: 읽기 -> +1 -> 쓰기
            // 두 쓰레드가 동시에 실행하면 중간 단계가 엉켜 최종 결과가 누락될 수 있음
            value++;
        }

        public int get() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();

        // 작업을 수행할 Runnable (카운터 100,000번 증가)
        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        long start = System.nanoTime();
        t1.start();
        t2.start();

        // 두 쓰레드 종료 대기
        t1.join();
        t2.join();
        long end = System.nanoTime();

        System.out.println("Expected: 200000");
        System.out.println("Actual:   " + counter.get()); // 동기화 없으면 종종 200000 미만
        System.out.println("Elapsed:  " + (end - start)/1_000_000 + " ms");
    }
}


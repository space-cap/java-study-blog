package ch13;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private AtomicInteger count = new AtomicInteger(0);

    // 락프리 방식의 증가 메서드
    public void increment() {
        while (true) {
            int current = count.get();
            int next = current + 1;
            // CAS(Compare-And-Set) 연산 시도
            if (count.compareAndSet(current, next)) {
                break; // 성공하면 루프 탈출
            }
            // 실패하면 다시 시도 (다른 스레드가 값을 바꿨을 수 있음)
        }
    }

    public int get() {
        return count.get();
    }


    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest counter = new AtomicIntegerTest();

        // 1000개의 스레드가 동시에 카운터를 1000번씩 증가
        int threadCount = 1000;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // 모든 스레드가 끝날 때까지 대기
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        // 결과 출력: 1000 x 1000 = 1,000,000
        System.out.println("최종 카운터 값: " + counter.get());
    }
}

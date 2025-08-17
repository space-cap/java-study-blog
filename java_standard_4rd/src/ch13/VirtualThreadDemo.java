package ch13;

// File: VirtualThreadDemo.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        // 작업당 가상 쓰레드 하나
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000; i++) {
                es.submit(() -> {
                    for (int j = 0; j < 1_000; j++) {
                        counter.incrementAndGet();
                    }
                });
            }
            // try-with-resources가 close를 호출하면 graceful shutdown
        }

        // executor 종료 대기 (이미 close로 initiate 되었음)
        // 실제로는 close가 완료될 때까지 블록되므로 추가 대기가 필요 없지만,
        // 데모로 안전하게 조금 더 기다려 준다.
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Result: " + counter.get()); // 1_000_000 기대
    }
}

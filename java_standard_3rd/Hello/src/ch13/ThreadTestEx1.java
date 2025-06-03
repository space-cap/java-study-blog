package ch13;

class Counter {
    private int count = 0;

    //    public synchronized void increment() {
//        count++; // 문제 지점: 비원자적(non-atomic) 연산
//    }
    public void increment() {
        synchronized (this) { // this 객체 락 사용
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class ThreadTestEx1 {
    public static void main(String[] args) throws Exception {
        System.out.println("main thread start!");
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("최종 값: " + counter.getCount()); // 2000 미만 출력 (예: 1834)

    }
}

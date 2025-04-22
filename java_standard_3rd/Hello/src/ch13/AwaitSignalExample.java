package ch13;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignalExample {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean ready = false;

    public static void main(String[] args) {
        // 1. 대기하는 스레드
        Thread waiter = new Thread(() -> {
            lock.lock();
            try {
                while (!ready) {
                    System.out.println("waiter: 조건이 false, 대기합니다.");
                    condition.await(); // 조건이 true가 될 때까지 대기
                }
                System.out.println("waiter: 조건이 true, 실행합니다!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // 2. 신호를 주는 스레드
        Thread signaler = new Thread(() -> {
            try {
                Thread.sleep(1000); // 1초 후 신호 보내기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                ready = true;
                System.out.println("signaler: 조건을 true로 만들고 신호를 보냅니다.");
                condition.signal(); // 대기 중인 스레드 하나를 깨움
            } finally {
                lock.unlock();
            }
        });

        waiter.start();
        signaler.start();
    }
}

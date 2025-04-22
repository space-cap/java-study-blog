package ch13;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private int balance = 0; // 계좌 잔액
    private final StampedLock lock = new StampedLock();

    // 낙관적 읽기 락을 활용한 잔액 조회 메서드
    public int getBalance() {
        // 1. 낙관적 읽기 락(Optimistic Read Lock) 시도
        long stamp = lock.tryOptimisticRead();
        int curBalance = this.balance; // 락 없이 읽기

        // 2. 읽는 동안 balance가 변경되지 않았는지 검증
        if (!lock.validate(stamp)) {
            // 3. 변경이 감지되면, 정식 읽기 락 획득 후 다시 읽기
            stamp = lock.readLock();
            try {
                curBalance = this.balance;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return curBalance;
    }

    // 입금 메서드 (쓰기 락 사용)
    public void deposit(int amount) {
        long stamp = lock.writeLock();
        try {
            this.balance += amount;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // 출금 메서드 (쓰기 락 사용)
    public void withdraw(int amount) {
        long stamp = lock.writeLock();
        try {
            this.balance -= amount;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // 테스트 코드
    public static void main(String[] args) throws InterruptedException {
        StampedLockTest account = new StampedLockTest();

        // 1. 입금/출금 스레드 생성
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) account.deposit(10);
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) account.withdraw(5);
        });

        t1.start();
        t2.start();

        // 2. 잔액 읽기 스레드 생성 (낙관적 읽기)
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int bal = account.getBalance();
                System.out.println("현재 잔액: " + bal);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        reader.start();

        t1.join();
        t2.join();
        reader.join();

        System.out.println("최종 잔액: " + account.getBalance());
    }
}

package ch13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int balance;
    public final ReentrantLock lock = new ReentrantLock();

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

public class TransferExample1 {
    // 두 락을 모두 tryLock으로 시도, 실패 시 롤백
    public static boolean transfer(Account from, Account to, int amount, long timeout, TimeUnit unit) throws InterruptedException {
        if (from.lock.tryLock(timeout, unit)) {
            try {
                if (to.lock.tryLock(timeout, unit)) {
                    try {
                        // 이체 로직
                        if (from.getBalance() < amount) return false;
                        from.withdraw(amount);
                        to.deposit(amount);
                        return true;
                    } finally {
                        to.lock.unlock();
                    }
                }
            } finally {
                from.lock.unlock();
            }
        }
        // 락 획득 실패
        return false;
    }

    public static void main(String[] args) {
        Account acc1 = new Account(1000);
        Account acc2 = new Account(1000);

        Thread t1 = new Thread(() -> {
            try {
                if (transfer(acc1, acc2, 100, 1, TimeUnit.SECONDS)) {
                    System.out.println("t1: 이체 성공");
                } else {
                    System.out.println("t1: 이체 실패(락 획득 실패)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                if (transfer(acc2, acc1, 200, 1, TimeUnit.SECONDS)) {
                    System.out.println("t2: 이체 성공");
                } else {
                    System.out.println("t2: 이체 실패(락 획득 실패)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}

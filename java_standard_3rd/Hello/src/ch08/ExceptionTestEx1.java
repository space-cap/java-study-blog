package ch08;

import java.io.FileInputStream;
import java.io.IOException;

// 사용자 정의 예외 (Checked Exception)
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// 사용자 정의 예외 (Unchecked Exception)
class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}

class BankAccount {
    private int balance = 1000;

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("잔액 부족: " + (amount - balance) + "원 부족");
        }
        balance -= amount;
    }
}

class MyResource implements AutoCloseable {
    void doWork() {
        System.out.println("리소스 사용 중");
    }

    @Override
    public void close() {
        System.out.println("리소스 정상 종료");
    }
}


public class ExceptionTestEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, ExceptionTestEx1");

        try {
            int result = 10 / 0;  // ArithmeticException 발생
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다: " + e.getMessage());
        } finally {
            System.out.println("연산 시도 완료");
        }

        BankAccount account = new BankAccount();
        try {
            account.withdraw(1500);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());  // 잔액 부족: 500원 부족
        }

        // try-with-resources
        try (MyResource res = new MyResource();
             FileInputStream fis = new FileInputStream("file.txt")) {

            res.doWork();
            // 파일 읽기 작업

        } catch (IOException e) {
            System.out.println("파일 오류: " + e.getMessage());
        }
    }
}

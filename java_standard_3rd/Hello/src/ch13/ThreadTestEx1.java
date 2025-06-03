package ch13;

class Counter {
    private int count = 0;

    public void increment() {
        count++; // 문제 지점: 비원자적(non-atomic) 연산
    }

    public int getCount() { return count; }
}

public class ThreadTestEx1 {
    public static void main(String[] args) {
        System.out.println("main thread start!");


    }
}

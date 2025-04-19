package ch13;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable 스레드 실행 중!");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
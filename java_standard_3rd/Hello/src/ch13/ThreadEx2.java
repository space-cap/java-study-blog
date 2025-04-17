package ch13;

public class ThreadEx2 {
    public static void main(String[] args) {
        ThreadEx2_1 t1 = new ThreadEx2_1();
        t1.start(); // Start the first thread

        System.out.println("ThreadEx2 main end");
    }
}

class ThreadEx2_1 extends Thread {
    public void run() {
        throwException();

        System.out.println("ThreadEx2_1 end");
    }
    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
